package com.Be1StopClick.configuration;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/*
 * Created by dendy-prtha on 15/03/2019.
 * Spring Security Configuration
 */

@EnableWebSecurity(debug = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //- Overriding the default HTTP based authentication which is Basic Authentication.
        http
                //- disable csrf protection on the server since we are using token based authentication.
                .csrf()
                .disable()
                .authorizeRequests()
                // - Allow access to static assets. This is if you also serve front-end
                //from the same server as backend, those calls are not required to be authenticated
                .antMatchers("/css/**", "/js/**", "/images/**", "/static/**", "/**/favicon.ico").permitAll()
                //- Allow HTTP POST to our server at /login request
                .antMatchers(HttpMethod.POST, "/auth/**").permitAll()
                //- Allow all requests on URL patterns /rest/*, where our protected resources
                //live and we also allow top level root access /, to serve index.html
                .antMatchers("/rest/**").permitAll()
                .antMatchers("/user/**").permitAll()
                .antMatchers("/movies/**").permitAll()
                .antMatchers("/musics/**").permitAll()
                //- configure that any other type of request to our server otherwise should be authenticated.
                .antMatchers("/signup/**").permitAll()
                .antMatchers("/").permitAll()
                //- For /login and /rest/* based endpoints, our filters LoginFilter and RestFilter intercept
                //  and validate the request before forwarding it to the endpoints to serve.
                .anyRequest()
                .authenticated();
    }
}

