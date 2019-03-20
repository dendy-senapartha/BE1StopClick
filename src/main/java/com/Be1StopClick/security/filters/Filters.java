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

    @Autowired
    public Filters(LoginFilter loginFilter, RestFilter restFilter) {
        this.loginFilter = loginFilter;
        this.restFilter = restFilter;
    }

    @Bean
    public FilterRegistrationBean loginRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(loginFilter);
        filterRegistrationBean.setUrlPatterns(Collections.singletonList("/auth/*"));
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean restRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(restFilter);
        List<String> urls = new ArrayList<String>();

        urls.add("/rest/*");
        urls.add("/user/*");
        //filterRegistrationBean.setUrlPatterns(Collections.singletonList("/rest/*"));
        filterRegistrationBean.setUrlPatterns(urls);
        return filterRegistrationBean;
    }
}
