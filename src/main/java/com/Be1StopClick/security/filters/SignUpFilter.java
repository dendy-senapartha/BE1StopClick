package com.Be1StopClick.security.filters;

import com.Be1StopClick.model.User;
import com.Be1StopClick.model.UserProfile;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/*
 * Created by dendy-prtha on 27/03/2019.
 * filter handler for signup
 */

@Component
public class SignUpFilter implements Filter {

    public static final String REGISTER_USER = "register-user";

    @Autowired
    public SignUpFilter() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        String action = httpRequest.getParameter(RequestParam.ACTION);

        if (httpRequest.getMethod().equalsIgnoreCase("POST")) {
            if(action.equalsIgnoreCase(REGISTER_USER))
            {
                httpRequest.getRequestDispatcher("user/insert").forward(httpRequest, httpResponse);
                return;
            }
        } else {

        }

        httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        return;
    }


    @Override
    public void destroy() {
    }
}
