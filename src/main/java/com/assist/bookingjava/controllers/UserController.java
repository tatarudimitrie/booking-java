package com.assist.bookingjava.controllers;

import com.assist.bookingjava.model.Admin;
import com.assist.bookingjava.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private AdminRepository adminRepository;

    private static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    @CrossOrigin
    @RequestMapping(path="/login", method = RequestMethod.POST)
    public ResponseEntity<String> login(@RequestBody Admin admin){

        Admin currentAdmin = new Admin();
        currentAdmin = adminRepository.findByEmail(admin.getEmail());

        String pass = currentAdmin.getPass();
        String inputPass = admin.getPass();

        if(!(currentAdmin.getEmail().equals(""))&& PASSWORD_ENCODER.matches(inputPass, pass)) {
            currentAdmin = adminRepository.findByEmail(admin.getEmail());
            return new ResponseEntity<>("Session created " + currentAdmin.getEmail() , HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
        }
    }

    private String encryptPassword(String inputPass) {
        return PASSWORD_ENCODER.encode(inputPass);
    }
}