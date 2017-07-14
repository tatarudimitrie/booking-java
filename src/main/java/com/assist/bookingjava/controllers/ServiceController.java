package com.assist.bookingjava.controllers;

import com.assist.bookingjava.model.Service;
import com.assist.bookingjava.services.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ServiceController {

    @Autowired
    ServiceService serviceService;

    @RequestMapping(method = RequestMethod.GET, value = "/services")
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

    @RequestMapping(method = RequestMethod.GET, value = "/services/input")
    public String addBulkService () {
        return serviceService.addBulkService();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/services")
    public String addService(@RequestBody Service service){
        return serviceService.addService(service);
    }
    @RequestMapping(method = RequestMethod.PUT, value = "/services")
    public void editService(@RequestBody Service service){
        serviceService.editService(service);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/services/{id}")
    public String deleteService(@PathVariable long id){
        return serviceService.deleteService(id);
    }
}