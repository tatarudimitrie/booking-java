package com.assist.bookingjava.controllers;

import com.assist.bookingjava.model.Admin;
import com.assist.bookingjava.model.Company;
import com.assist.bookingjava.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by cosmin on 12.07.2017.
 */

@RestController
public class CompanyController {

    @Autowired
    CompanyRepository companyRepository;

    @RequestMapping(method= RequestMethod.GET, value="/companies/input/{id1c}")
    public String process(@RequestBody Admin admin){
        Company company = new Company();
        company.setDescription("Info Darex");
        company.setName("Darex");
        company.setImage_url("test");

//        company.setAdmin();
//admin.GETCOMPANYLIST.ADD()
       // companyRepository.save(new Company(null, "Darex", "Info Darex","test"));
       // companyRepository.save(new Company(null, "Assist","Info Assist","test"));

        return "Done";
    }

    @RequestMapping(method=RequestMethod.GET, value="/companies")
    public String findAll(){
        String result = "<html>";

        for(Company cust : companyRepository.findAll()){
            result += "<div>" + cust.toString() + "</div>";
        }

        return result + "</html>";
    }

    @RequestMapping( method=RequestMethod.GET, value="/companies/id/{id}")
    public String findById(@PathVariable long id){
        String result = "";
        result = companyRepository.findOne(id).toString();
        return result;
    }

    @RequestMapping(method=RequestMethod.GET, value="/companies/name/{name}"  )
    public String fetchDataByName(@PathVariable String name){
        String result = "<html>";

        for(Company company: companyRepository.findByName(name)){
            result += "<div>" + company.toString() + "</div>";
        }

        return result + "</html>";
    }

    @RequestMapping(method=RequestMethod.POST, value="/companies")
    public void addCompany(@RequestBody Company company) {
       companyRepository.save(company);
    }

    @RequestMapping(method=RequestMethod.PUT, value="/companies")
    public void editCompany(@RequestBody Company company) {
        companyRepository.save(company);
    }

    @RequestMapping(method=RequestMethod.DELETE, value="/companies/{id}")
    public void deleteCompany(@PathVariable long id) {
        companyRepository.delete(id);
    }
}
