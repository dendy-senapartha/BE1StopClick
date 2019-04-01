package com.Be1StopClick.configuration;

import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.util.Properties;

/*
 * Created by dendy-prtha on 01/03/2019.
 * Web MVC Configuration class.
 * configurer implement method since spring 5.0. older version using deprecated WebMvcConfigurerAdapter
 * below code equals with dipatcher-servlet.xml at resources file.
 */

@EnableWebMvc
@PropertySource({"classpath:application.properties"})
@ComponentScan(basePackages = {"com.Be1StopClick.controllers", "com.Be1StopClick.mail"})
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private Environment env;

    //@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String resourceHandler = Preconditions.checkNotNull(env.getProperty("spring.mvc.resource.handler"));
        String resourceLocation = Preconditions.checkNotNull(env.getProperty("spring.mvc.resource.location"));
        registry.addResourceHandler(resourceHandler).
                addResourceLocations(resourceLocation).
                setCachePeriod(31556926);
    }

    /**/
    @Bean
    public InternalResourceViewResolver resolver() {
        String viewPrefix = Preconditions.checkNotNull(env.getProperty("spring.mvc.view.prefix"));
        String viewSufix = Preconditions.checkNotNull(env.getProperty("spring.mvc.view.suffix"));
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setViewClass(JstlView.class);
        resolver.setPrefix(viewPrefix);
        resolver.setSuffix(viewSufix);
        return resolver;
    }

    @Bean
    public RequestMappingHandlerAdapter getRequestMappingHandlerAdapter() {
        return new RequestMappingHandlerAdapter();
    }

    public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
        return new RequestMappingHandlerMapping();
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //this will map uri to jsp view directly without a controller
        registry.addViewController("/accessDenied");
    }

    private final long MAX_AGE_SECS = 3600;

    //enable CORS (Cross-Origin Resource sharing) mapping
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(MAX_AGE_SECS);
    }

    @Bean
    public SimpleMailMessage templateSimpleMessage() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setText("This is the test email template for your email:\n%s\n");
        return message;
    }

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername("dendyprtha@gmail.com");
        mailSender.setPassword("mzhxdyqrmbmokcpd");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }

}
