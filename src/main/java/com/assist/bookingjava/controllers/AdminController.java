/*
* Admin Controller
*
* GET, POST, PUT, DELETE
*
* */

package com.assist.bookingjava.controllers;

import com.assist.bookingjava.model.Admin;
import com.assist.bookingjava.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AdminController {

    @Autowired
    AdminRepository adminRepository;

    @RequestMapping(method=RequestMethod.GET, value="/admins")
    public ResponseEntity findAllAdmins(){
        List<Admin> adminList = new ArrayList<>();

        for(Admin a : adminRepository.findAll()) {
            adminList.add(a);
        }

        return ResponseEntity.ok(adminList);
    }

    @RequestMapping(method=RequestMethod.GET, value="/admins/id/{id}")
    public Admin getAdminById(@PathVariable long id) {
        return adminRepository.findOne(id);
    }

    @RequestMapping(method=RequestMethod.GET, value="/admins/name/{name}")
    public List<Admin> getAdminByName(@PathVariable String name){
        List<Admin> adminList = new ArrayList<>();

        for(Admin a: adminRepository.findByName(name)){
            adminList.add(a);
        }

        return adminList;
    }

    @RequestMapping(method=RequestMethod.GET, value="/admins/email/{email}")
    public List<Admin> getAdminByEmail(@PathVariable String email){
        List<Admin> adminList = new ArrayList<>();

        for(Admin a: adminRepository.findByEmail(email)){
            adminList.add(a);
        }

        return adminList;
    }

    @RequestMapping(method=RequestMethod.GET, value="/admins/input")
    public String bulkAddAdmin() {
        adminRepository.save(new Admin("Jack", "jack@assist.ro", "Jack_pass"));
        adminRepository.save(new Admin("Adam Johnson", "johnson@assist.ro", "Johnson_pass"));
        adminRepository.save(new Admin("Kim Smith", "kim@assist.ro", "Kim_pass"));
        adminRepository.save(new Admin("David Williams", "david@assist.ro", "David_pass"));
        adminRepository.save(new Admin("Peter Davis", "peter@assist.ro", "Peter_pass"));
        return "Done";
    }

    @RequestMapping(method=RequestMethod.PUT, value="/admins")
    public String addAdmin(@RequestBody Admin admin) {

        if (adminRepository.findByEmail(admin.getEmail()).isEmpty()) {
            adminRepository.save(admin);
            return "OK";
        } else {
            return "Duplicate mail";
        }

        //TODO PASS ENCRYPT
        //TODO CHECK PASS LENGTH
    }

    @RequestMapping(method=RequestMethod.POST, value="/admins")
    public void editAdmin(@RequestBody Admin admin)
    {
        adminRepository.save(admin);
    }

    @RequestMapping(method=RequestMethod.DELETE, value="/admins/{id}")
    public void deleteAdmin(@PathVariable long id)
    {
        adminRepository.delete(id);
    }
}

/*      ADMIN JSON TEMPLATE
        {"name" : "Admin name", "mail" : "admin@assist.ro", "pass" : "pass"}
*/