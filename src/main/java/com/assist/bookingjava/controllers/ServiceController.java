package com.assist.bookingjava.controllers;

import com.assist.bookingjava.repositories.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceController {

    @Autowired
    ServiceRepository serviceRepository;

    @RequestMapping("/services")
    public String process(){
        return "Done";
    }
}
