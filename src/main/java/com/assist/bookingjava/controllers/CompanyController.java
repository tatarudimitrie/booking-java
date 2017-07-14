package com.assist.bookingjava.controllers;

import com.assist.bookingjava.model.Company;
import com.assist.bookingjava.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @RequestMapping(method=RequestMethod.GET, value="/companies")
    public ResponseEntity findAll(){
        return companyService.findAllCompanies();
    }

    @RequestMapping( method=RequestMethod.GET, value="/companies/id/{id}")
    public ResponseEntity findCompanyById(@PathVariable long id){
        return companyService.findCompanyById(id);
    }

    @RequestMapping(method=RequestMethod.GET, value="/companies/name/{name}"  )
    public ResponseEntity findCompanyByName(@PathVariable String name){
        return companyService.findCompanyByName(name);
    }

    @RequestMapping(method= RequestMethod.GET, value="/companies/input")
    public String addBulkCompany(){
        return companyService.addBulkCompany();
    }

    @RequestMapping(method=RequestMethod.POST, value="/companies")
    public String addCompany(@RequestBody Company company) {
        return companyService.addCompany(company);
    }

    @RequestMapping(method=RequestMethod.PUT, value="/companies")
    public void editCompany(@RequestBody Company company) {
        companyService.editCompany(company);
    }

    @RequestMapping(method=RequestMethod.DELETE, value="/companies/{id}")
    public String deleteCompany(@PathVariable long id) {
        return companyService.deleteCompany(id);
    }
}
