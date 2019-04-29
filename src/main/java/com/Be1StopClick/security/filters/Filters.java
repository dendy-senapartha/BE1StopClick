package com.Be1StopClick.security.filters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by dendy-prtha on 20/03/19.
 * this class used to register the filters so that they are active and intercept the incoming calls
 */

@Configuration
public class Filters {

    private final LoginFilter loginFilter;
    private final RestFilter restFilter;
    private final SignUpFilter signUpFilter;

    @Autowired
    public Filters(LoginFilter loginFilter, RestFilter restFilter, SignUpFilter signUpFilter) {
        this.loginFilter = loginFilter;
        this.restFilter = restFilter;
        this.signUpFilter = signUpFilter;
    }

    @Bean
    public FilterRegistrationBean loginRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(loginFilter);
        filterRegistrationBean.setUrlPatterns(Collections.singletonList("/auth/*"));
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean signUpRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(signUpFilter);
        filterRegistrationBean.setUrlPatterns(Collections.singletonList("/signup/*"));
        return filterRegistrationBean;
    }

    //default filter
    @Bean
    public FilterRegistrationBean defaultRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(restFilter);
        List<String> urls = new ArrayList<String>();
        urls.add("/rest/*");
        urls.add("/user/*");
        urls.add("/movies/*");
        urls.add("/musics/*");
        //filterRegistrationBean.setUrlPatterns(Collections.singletonList("/rest/*"));
        filterRegistrationBean.setUrlPatterns(urls);
        return filterRegistrationBean;
    }
}
