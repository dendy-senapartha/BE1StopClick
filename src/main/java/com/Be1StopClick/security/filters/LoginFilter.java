package com.Be1StopClick.security.filters;

import com.Be1StopClick.dao.UserDao;
import com.Be1StopClick.exception.InvalidTokenException;
import com.Be1StopClick.security.AuthProvider;
import com.Be1StopClick.model.User;
import com.Be1StopClick.model.UserProfile;
import com.Be1StopClick.security.AppTokenProvider;
import com.Be1StopClick.security.GoogleTokenVerifier;
import com.Be1StopClick.util.IdUtility;
import com.alibaba.fastjson.JSON;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by dendy-prtha on 20/03/19.
 * This class intercepts the incoming call to /login, if the X-ID-TOKEN is present it validates further,
 * if not, it does not forwards the request to API endpoint and return HTTP 401 Unauthorized.
 */

@Component
public class LoginFilter implements Filter {

    public static final String USER_LOGIN = "user-login";
    public static final String USER_FORGOT_PASSWORD = "user-forgot-password";

    private GoogleTokenVerifier googleTokenVerifier;

    @Autowired
    private UserDao userRepository;

    @Autowired
    public LoginFilter(GoogleTokenVerifier googleTokenVerifier) {
        this.googleTokenVerifier = googleTokenVerifier;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    /*
    request param header
    X-ID-TOKEN : id Token
    PROVIDER : social login provider
    * */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        String errMsg = null;
        String action = httpRequest.getParameter(RequestParam.ACTION);
        if (httpRequest.getMethod().equalsIgnoreCase("POST")) {
            switch (action) {
                case USER_LOGIN:
                    errMsg = doLogin(httpRequest, httpResponse);
                    if (errMsg == null)
                        return;
                    break;
                case USER_FORGOT_PASSWORD:
                    errMsg = sendEmail(httpRequest, httpResponse);
                    if (errMsg == null)
                        return;
                    return;
                case "ASu":
                    break;

            }
        }

        httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, errMsg);
        return;
    }

    @Override
    public void destroy() {
    }

    private String sendEmail(HttpServletRequest httpRequest, HttpServletResponse httpResponse)
            throws IOException, ServletException {
        String errMsg = null;
        httpRequest.getRequestDispatcher("/auth/forget-password").forward(httpRequest, httpResponse);
        return errMsg;
    }

    private String doLogin(HttpServletRequest httpRequest, HttpServletResponse httpResponse)
            throws IOException, ServletException {
        String errMsg = null;
        String idToken = httpRequest.getHeader("X-ID-TOKEN");
        String loginProvider = httpRequest.getHeader("PROVIDER");
        if (loginProvider != null) {
            if (idToken != null) {
                try {
                    switch (loginProvider) {
                        case AuthProvider.GOOGLE:
                            verifyGoogleToken(idToken, httpResponse);
                            break;
                        case AuthProvider.FACEBOOK:
                            break;
                        case AuthProvider.GITHUB:
                            break;
                    }
                    //redirect to social login
                    httpRequest.getRequestDispatcher("/auth/social-login").forward(httpRequest, httpResponse);
                    //filterChain.doFilter(httpRequest, httpResponse);
                } catch (GeneralSecurityException | InvalidTokenException e) {
                    // This is not a valid token, we will send HTTP 401 back
                    errMsg = e.getMessage();
                }
            } else {
                if (loginProvider.equalsIgnoreCase(AuthProvider.LOCAL)) {
                    httpRequest.getRequestDispatcher("/auth/local-login").forward(httpRequest, httpResponse);
                } else {
                    errMsg = "no token!";
                }
            }
        } else {
            errMsg = "no provider!";
        }
        return errMsg;
    }

    private void verifyGoogleToken(String idToken, HttpServletResponse httpResponse)
            throws GeneralSecurityException, IOException, InvalidTokenException {
        Payload payload;
        payload = googleTokenVerifier.verify(idToken);
        if (payload != null) {
            //check if user are available
            Optional<User> userOptional = userRepository.findByEmail(payload.getEmail());
            User user;
            if (userOptional.isPresent()) {
                user = userOptional.get();
                updateExistingUser(user, payload);
            } else {
                registerNewUser(payload, AuthProvider.GOOGLE);
            }
            String userId = payload.getSubject();
            //this one fakin pass by refrence
            AppTokenProvider.addAuthentication(httpResponse, userId);
        }
    }

    private User registerNewUser(Payload userPayLoad, String provider) {
        User user = new User();
        userPayLoad.getHostedDomain();
        user.setProvider(provider);
        user.setProviderId(userPayLoad.getSubject());
        user.setEmail(userPayLoad.getEmail());
        UserProfile profile = new UserProfile();
        profile.setName(userPayLoad.get("name").toString());
        //profile.setImageUrl(userPayLoad.get("picture").toString());
        user.setUserProfile(profile);
        if (userRepository.save(user)) {
            return user;
        }
        return null;
    }

    private User updateExistingUser(User existingUser, Payload userPayLoad) {
        UserProfile profile = existingUser.getUserProfile();
        profile.setName(userPayLoad.get("name").toString());
        //profile.setImageUrl(userPayLoad.get("picture").toString());
        existingUser.setEmail(userPayLoad.get("email").toString());
        existingUser.setEmailVerified(Boolean.valueOf(userPayLoad.getEmailVerified()));
        existingUser.setUserProfile(profile);
        if (userRepository.update(existingUser)) {
            return existingUser;
        }
        return null;
    }


}
