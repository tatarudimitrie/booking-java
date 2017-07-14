/*
* Admin Controller
*
* CRUD OPERATIONS
*
* POST      = C create
* GET       = R read
* PUT       = U update
* DELETE    = D delete
*
* */

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

    @RequestMapping(method=RequestMethod.GET, value="/admins")
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

    @RequestMapping(method=RequestMethod.GET, value="/admins/email/{email}")
    public ResponseEntity findAdminByEmail(@PathVariable String email){
        return adminService.findAdminByEmail(email);
    }

    @RequestMapping(method=RequestMethod.GET, value="/admins/input")
    public String bulkAddAdmin() {
        return adminService.bulkAddAdmin();
    }

    @RequestMapping(method=RequestMethod.PUT, value="/admins")
    public String editAdmin(@RequestBody Admin admin) {
        return adminService.editAdmin(admin);
    }

    @RequestMapping(method=RequestMethod.POST, value="/admins")
    public String addAdmin(@RequestBody Admin admin) {
        return adminService.addAdmin(admin);
    }

    @RequestMapping(method=RequestMethod.DELETE, value="/admins/{id}")
    public String deleteAdmin(@PathVariable long id) {
        return adminService.deleteAdmin(id);
    }
}

/*      ADMIN JSON TEMPLATE
        { "name" : "Admin name", "mail" : "admin@assist.ro", "pass" : "pass" }
*/