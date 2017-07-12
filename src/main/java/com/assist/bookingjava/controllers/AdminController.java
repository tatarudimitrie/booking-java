/**
 * Created by myt on 12.07.2017.
 */
package com.assist.bookingjava.controllers;

import com.assist.bookingjava.model.Admins;
import com.assist.bookingjava.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @Autowired
    AdminRepository repository;

    @RequestMapping("/save")
    public String process(){
        repository.save(new Admins("Jack", "Smith"));
        repository.save(new Admins("Adam", "Johnson"));
        repository.save(new Admins("Kim", "Smith"));
        repository.save(new Admins("David", "Williams"));
        repository.save(new Admins("Peter", "Davis"));
        return "Done";
    }


    @RequestMapping("/findall")
    public String findAll(){
        String result = "<html>";

        for(Admins cust : repository.findAll()){
            result += "<div>" + cust.toString() + "</div>";
        }

        return result + "</html>";
    }

    @RequestMapping("/findbyid")
    public String findById(@RequestParam("id") long id){
        String result = "";
        result = repository.findOne(id).toString();
        return result;
    }

    @RequestMapping("/findbylastname")
    public String fetchDataByLastName(@RequestParam("lastname") String lastName){
        String result = "<html>";

        for(Admins cust: repository.findByLastName(lastName)){
            result += "<div>" + cust.toString() + "</div>";
        }

        return result + "</html>";
    }
}