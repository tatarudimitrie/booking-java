package com.assist.bookingjava.controllers;

import com.assist.bookingjava.model.Admin;
import com.assist.bookingjava.model.Service;
import com.assist.bookingjava.repositories.AdminRepository;
import com.assist.bookingjava.repositories.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by root on 13.07.2017.
 */
@RestController
public class ServiceController {


    @Autowired
    ServiceRepository repository;

    @RequestMapping("/services")
    public String process(){
        Date data=new Date();
     /*  repository.save(new Service("Sd",
                "Andrei",
                "Leonte",
                 data,
                10.2
                ));*/

        return "Done";
    }
}
