package com.assist.bookingjava.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @RequestMapping(path="/login", method = RequestMethod.POST)
    public ResponseEntity<String> login(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            //String currentUserName = authentication.getName();
            return new ResponseEntity<>("Session created for ", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Something went wrong", HttpStatus.OK);
        }
    }
}