package com.assist.bookingjava.controllers;

import com.assist.bookingjava.model.Admin;
import com.assist.bookingjava.model.Company;
import com.assist.bookingjava.repositories.AdminRepository;
import com.assist.bookingjava.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by cosmin on 12.07.2017.
 */

@RestController
public class CompanyController {

    @Autowired
    CompanyRepository repository;

    @RequestMapping("/saveCompany")
    public String process(){
        repository.save(new Company(1, "Darex", "Info Darex","test"));
        repository.save(new Company(2, "Assist","Info Assist","test"));

        return "Done";
    }


    @RequestMapping("/findallCompany")
    public String findAll(){
        String result = "<html>";

        for(Company cust : repository.findAll()){
            result += "<div>" + cust.toString() + "</div>";
        }

        return result + "</html>";
    }

    @RequestMapping("/findbyidCompany")
    public String findById(@RequestParam("idCompany") long idCompany){
        String result = "";
        result = repository.findOne(idCompany).toString();
        return result;
    }

    @RequestMapping("/findbynameCompany")
    public String fetchDataByNameCompany(@RequestParam("nameCompany") String nameCompany){
        String result = "<html>";

        for(Company cust: repository.findByNameCompany(nameCompany)){
            result += "<div>" + cust.toString() + "</div>";
        }

        return result + "</html>";
    }
}
