package com.Be1StopClick.security.filters;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static com.Be1StopClick.security.AppTokenProvider.*;

/**
 * Created by dendy-prtha on 20/03/19.
 * This class intercepts every call coming to /rest based URL pattern. It checks for Authorization header
 * and validates the token if present. It also then adds a new refreshed Authorization header that client may
 * want to use for further communication. If the token is not present, or is invalid, the call does not
 * hits the endpoints and return HTTP 401 UnAuthorized.
 */

@Component
public class RestFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        Optional<String> userFromToken = getUserFromToken(request);
        //TODO: need to check if user isvalid
        if (!userFromToken.isPresent()) {
            response.sendError(HttpStatus.UNAUTHORIZED.value(),"you have no permission!" );
            return;
        }
        request.setAttribute("userId", userFromToken.get());
        addAuthentication(response, userFromToken.get());
        filterChain.doFilter(request, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
