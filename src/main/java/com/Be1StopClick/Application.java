package com.Be1StopClick;


import com.Be1StopClick.configuration.OAuthProperties;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

/*
 * Created by dendy-prtha on 01/03/2019.
 * Springboot application runner
 */

@SpringBootApplication
public class Application //extends SpringBootServletInitializer
{

    //spring entry point for war package
    //@Override
    //protected SpringApplicationBuilder configure (SpringApplicationBuilder builder) {
      //  return builder.sources(Application.class);
    //}


    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        app.run(args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
