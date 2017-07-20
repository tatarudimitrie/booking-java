package com.assist.bookingjava.controllers;

import com.assist.bookingjava.model.Admin;
import com.assist.bookingjava.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping(method=RequestMethod.GET, value="/admins/all")
    public ResponseEntity findAllAdmins(){
        return adminService.findAllAdmins();
    }

    @RequestMapping(method=RequestMethod.GET, value="/admins/id/{id}")
    public ResponseEntity findAdminById(@PathVariable long id) {
        return adminService.findAdminById(id);
    }

    @RequestMapping(method=RequestMethod.GET, value="/admins/name/{name}")
    public ResponseEntity findAdminByName(@PathVariable String name){
        return adminService.findAdminByName(name);
    }

    @RequestMapping(method=RequestMethod.PUT, value="/admins/edit")
    public ResponseEntity<String> editAdmin(@RequestBody Admin admin) {
        return adminService.editAdmin(admin);
    }

    @RequestMapping(method=RequestMethod.POST, value="/admins/add")
    public ResponseEntity<String> addAdmin(@RequestBody Admin admin) {
        return adminService.addAdmin(admin);
    }

    @RequestMapping(method=RequestMethod.DELETE, value="/admins/delete/{id}")
    public ResponseEntity<String> deleteAdmin(@PathVariable long id) {
        return adminService.deleteAdmin(id);
    }

    @RequestMapping(method=RequestMethod.GET, value="/admins/input")
    public String bulkAddAdmin() {
        return adminService.bulkAddAdmin();
    }
}