package com.assist.bookingjava.controllers;

import com.assist.bookingjava.model.Admin;
import com.assist.bookingjava.model.Company;
import com.assist.bookingjava.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @RequestMapping(method=RequestMethod.GET, value="/companies/all")
    public ResponseEntity findAllCompanies(){
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

    @RequestMapping(method=RequestMethod.PUT, value="/companies/edit")
    public ResponseEntity<String> editCompany(@RequestBody Company company) {
        return companyService.editCompany(company);
    }

    @RequestMapping(method=RequestMethod.POST, value="/companies/add")
    public ResponseEntity<String> addCompany(@RequestBody Company company) {
        return companyService.addCompany(company);
    }

    @RequestMapping(method=RequestMethod.DELETE, value="/companies/delete/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable long id) {
        return companyService.deleteCompany(id);
    }

    @RequestMapping(method=RequestMethod.POST, value="/companies/admin")
    public ResponseEntity findByAdminEmail(@RequestBody Admin admin) {
        return companyService.findByAdminEmail(admin);
    }

    @RequestMapping(method= RequestMethod.GET, value="/companies/input")
    public String addBulkCompany(){
        return companyService.addBulkCompany();
    }
}