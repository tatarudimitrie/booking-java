package com.assist.bookingjava.controllers;

import com.assist.bookingjava.model.Admin;
import com.assist.bookingjava.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class UserController {

    @Autowired
   private AdminRepository adminRepository;

    @CrossOrigin
    @RequestMapping(path="/login", method = RequestMethod.POST)
    public ResponseEntity<String> login(@RequestBody Admin admin){
Admin currentAdmin=new Admin();
 currentAdmin=adminRepository.findByEmail(admin.getEmail());

     if(!(currentAdmin.getEmail().equals(""))&& currentAdmin.getPass().equals(admin.getPass()) ) {

            currentAdmin= adminRepository.findByEmail(admin.getEmail());

        return new ResponseEntity<>("Session created " + currentAdmin.getEmail() , HttpStatus.OK);
     }
     else {

         return new ResponseEntity<>("Somethin went wrong", HttpStatus.BAD_REQUEST);
     }
    }
}