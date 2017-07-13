/*
* Admin Controller
*
* GET, POST, PUT, DELETE
*
* */

package com.assist.bookingjava.controllers;

import com.assist.bookingjava.model.Admin;
import com.assist.bookingjava.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdminController {

    @Autowired
    AdminRepository repository;

    @RequestMapping(method=RequestMethod.GET, value="/admins")
    public String findAllAdmins(){
        String result = "<html>";

        for(Admin a : repository.findAll()) {
            result += "<div>" + a.toString() + "</div>";
        }

        return result + "</html>";
    }

    @RequestMapping(method=RequestMethod.GET, value="/admins/id/{id}")
    public String findById(@PathVariable long id) {
        String result = repository.findOne(id).toString();
        return result;
    }

    @RequestMapping(method=RequestMethod.GET, value="/admins/name/{name}")
    public String fetchDataByLastName(@PathVariable String name){
        String result = "<html>";

        for(Admin a: repository.findByName(name)){
            result += "<div>" + a.toString() + "</div>";
        }

        return result + "</html>";
    }

    @RequestMapping(method=RequestMethod.GET, value="/admins/add")
    public String addAdmin() {
        repository.save(new Admin("Jack", "jack@assist.ro", "Jack_pass"));
        repository.save(new Admin("Adam Johnson", "johnson@assist.ro", "Johnson_pass"));
        repository.save(new Admin("Kim Smith", "kim@assist.ro", "Kim_pass"));
        repository.save(new Admin("David Williams", "david@assist.ro", "David_pass"));
        repository.save(new Admin("Peter Davis", "peter@assist.ro", "Peter_pass"));
        return "Done";
    }
}