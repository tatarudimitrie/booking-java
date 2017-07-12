package com.assist.bookingjava.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by whiteTiger on 10.07.2017.
 */
@Controller
public class MainController  {

    @RequestMapping("/")
    public String addViewControllers() {
        return "index";
    }
}
