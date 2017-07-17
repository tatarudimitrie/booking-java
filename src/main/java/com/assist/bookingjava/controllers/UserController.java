package com.assist.bookingjava.controllers;

import com.assist.bookingjava.model.Admin;
import com.assist.bookingjava.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 17.07.2017.
 */
@RestController
public class UserController {
    @Autowired
    private AdminRepository adminRepository;


    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String listUsero() {
        return "Succes";
    }
}