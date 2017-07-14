package com.assist.bookingjava.controllers;

import com.assist.bookingjava.model.Admin;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by whiteTiger on 10.07.2017.
 */
@Controller
@RequestMapping("/")
public class MainController  {


    @RequestMapping(value = "/echo/{in}", method = RequestMethod.GET)
    public String echo(@PathVariable(value = "in") final String in, @AuthenticationPrincipal final Admin user) {
        return "Hello " +  ", you said: " + in;

    }
}
