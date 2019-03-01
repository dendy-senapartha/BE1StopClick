package com.Be1StopClick.configuration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/*
 * Created by dendy-prtha on 01/03/2019.
 * App initializer class. This config will auto loaded by Springboot
 * With the release of the Servlet 3.0 spec it became possible to
 * configure your Servlet Container with (almost) no xml.
 * For this there is the ServletContainerInitializer in the Servlet specification.
 * In this class you can register filters, listeners, servlets etc. as you would traditionally do in a web.xml.
 * */

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{null};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebMvcConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
