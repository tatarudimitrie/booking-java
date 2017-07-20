package com.assist.bookingjava.controllers;

import com.assist.bookingjava.model.Company;
import com.assist.bookingjava.model.Schedule;
import com.assist.bookingjava.model.Service;
import com.assist.bookingjava.services.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ServiceController {

    @Autowired
    ServiceService serviceService;

    @RequestMapping(method = RequestMethod.GET, value = "/services/all")
    public ResponseEntity findAllServices() {
        return serviceService.findAllServices();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/services/id/{id}")
    public ResponseEntity findServiceById(@PathVariable long id) {
        return serviceService.findServiceById(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/services/name/{name}")
    public ResponseEntity findServiceByName(@PathVariable String name){
        return serviceService.findServiceByName(name);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/services/edit")
    public ResponseEntity<String> editService(@RequestBody Service service){
        return serviceService.editService(service);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/services/add")
    public ResponseEntity<String> addService(@RequestBody Service service,@RequestBody Schedule schedule){

        return serviceService.addService(service);



    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/services/delete/{id}")
    public ResponseEntity<String> deleteService(@PathVariable long id){
        return serviceService.deleteService(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/services/company")
    public ResponseEntity findByCompany(@RequestBody Company company){
        return serviceService.findServiceByCompany(company);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/services/input")
    public String addBulkService () {
        return serviceService.addBulkService();
    }
}