/*
package com.assist.bookingjava.Security;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

*/
/**
 * Created by root on 14.07.2017.
 *//*

@Configuration
@EnableWebSecurity
@ComponentScan(basePackageClasses = DemoAuthenticationFilter.class, useDefaultFilters = false, includeFilters = {
        @ComponentScan.Filter(org.springframework.stereotype.Controller.class) })
public class Webconfig extends WebMvcConfigurerAdapter {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("*/
/**")
                .allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH")
                .allowedHeaders("Content-Type", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
                        "Access-Control-Request-Headers")
                .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials")
                .allowCredentials(true).maxAge(3600);
    }

}*/
