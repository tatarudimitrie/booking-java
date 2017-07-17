package com.assist.bookingjava;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class Boot extends WebMvcConfigurerAdapter {


    public static void main(String[] args) {


        SpringApplication.run(Boot.class, args);
    }

}

