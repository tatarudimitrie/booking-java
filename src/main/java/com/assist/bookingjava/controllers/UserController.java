package com.assist.bookingjava.controllers;

import com.assist.bookingjava.model.Admin;
import com.assist.bookingjava.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 17.07.2017.
 */
@Controller
public class UserController {
    @Autowired
    private AdminRepository adminRepository;
    @RequestMapping(path="/login", method = RequestMethod.GET)
    public ResponseEntity listUser(){

        return new ResponseEntity(adminRepository.findByName("andrei"), HttpStatus.OK);
    }

    @RequestMapping(path="/login/{id}", method = RequestMethod.GET)
    public ResponseEntity  listUser(@PathVariable(value = "id") String id){
      //  return new ResponseEntity(adminRepository.findAll().stream().filter(user -> user.getId().equals(id)).findFirst().orElse(null), HttpStatus.OK);
        return new ResponseEntity(adminRepository.findByName("andrei"), HttpStatus.OK);
    }
    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public ResponseEntity listUser(@RequestBody Admin user) {
        return new ResponseEntity(adminRepository.findByName("andrei"), HttpStatus.OK);
    }
}