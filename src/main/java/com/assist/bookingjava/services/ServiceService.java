package com.assist.bookingjava.services;

import com.assist.bookingjava.model.Company;
import com.assist.bookingjava.model.Service;
import com.assist.bookingjava.repositories.CompanyRepository;
import com.assist.bookingjava.repositories.ServiceRepository;
import com.assist.bookingjava.services.interfaces.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@org.springframework.stereotype.Service
public class ServiceService implements ServiceInterface {

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private CompanyRepository companyRepository;

    private String nl = "\n";

    private String errorInput = "";

    public ResponseEntity findAllServices() {
        System.out.println(nl + "SERVICE GET: /services/all");

        try {
            List<Service> serviceList = new ArrayList<>();

            for (Service s : serviceRepository.findAll()) {
                serviceList.add(s);
            }

            System.out.println("OK: " + serviceList.toString());
            return ResponseEntity.ok(serviceList);
        } catch (Exception e) {
            System.out.println("BAD REQUEST!");
            return ResponseEntity.badRequest().body("Bad request! " + e.toString());
        }
    }

    public ResponseEntity findServiceById(long id) {
        System.out.println(nl + "SERVICE GET: /services/id/{" + id + "}");

        try {
            Service service = serviceRepository.findOne(id);

            System.out.println("OK: " + service.toString());
            return ResponseEntity.ok(service);
        } catch (Exception e) {
            System.out.println("BAD REQUEST!");
            return ResponseEntity.badRequest().body("Bad request! " + e.toString());
        }
    }

    public ResponseEntity findServiceByName(String name) {
        System.out.println(nl + "SERVICE GET: /services/name/{" + name + "}");

        try {
            List<Service> serviceList = serviceRepository.findByName(name);

            System.out.println("OK: " + serviceList.toString());
            return ResponseEntity.ok(serviceList);
        } catch (Exception e) {
            System.out.println("BAD REQUEST!");
            return ResponseEntity.badRequest().body("Bad request! " + e.toString());
        }
    }

    public ResponseEntity findServiceByCompany(Company company) {
        System.out.println(nl + "SERVICE (GET) POST: services/company for " + company.toString());

        try {
            Company currentCompany = companyRepository.findById(company.getId());
            List<Service> serviceList = serviceRepository.findByCompany(currentCompany);

            System.out.println("OK: " + serviceList.toString());
            return ResponseEntity.ok(serviceList);
        } catch (Exception e) {
            System.out.println("BAD REQUEST!");
            return ResponseEntity.badRequest().body("Bad request! " + e.toString());
        }
    }

    public ResponseEntity<String> editService(Service service) {
        System.out.println(nl + "SERVICE PUT: /services/edit - for " + service.toString());

        try {
            Service currentService = serviceRepository.findOne(service.getId());
            currentService.setName(service.getName());
            currentService.setDescription(service.getDescription());
            currentService.setDuration(service.getDuration());
            currentService.setFree_space(service.getFree_space());
            currentService.setPrice(service.getPrice());
            currentService.setSchedule(service.getSchedule());
            serviceRepository.save(currentService);

            System.out.println("OK: " + currentService.toString());
            return ResponseEntity.ok("Service was successfully edited!");
        } catch (Exception e) {
            System.out.println("BAD REQUEST!");
            return ResponseEntity.badRequest().body("Bad request! " + e.toString());
        }
    }

    public ResponseEntity<String> addService(Service service) {
        System.out.println(nl + "SERVICE POST: /services/add - for " + service.toString());

        try {
            Company company = companyRepository.findById(service.getCompany().getId());
            service.setCompany(company);
            serviceRepository.save(service);
            long id = service.getId();

            System.out.println("OK: ID: " + id + " | " + service.toString() + " was added for" + company.toString());
            return ResponseEntity.ok(String.valueOf(id));
        } catch (Exception e) {
            System.out.println("BAD REQUEST!");
            return ResponseEntity.badRequest().body("Bad request! " + e.toString());
        }
    }

    public ResponseEntity<String> deleteService(long id) {
        System.out.println(nl + "SERVICE DELETE: /services/delete - for service with id " + id );

        if (serviceNotExists(id)) {
            return ResponseEntity.badRequest().body("There is no service with id " + id + " in DB!");
        }

        try {
            serviceRepository.delete(id);

            System.out.println("OK: Deleted!");
            return ResponseEntity.ok("Service with id " + id + " was successfully deleted!");
        } catch (Exception e) {
            System.out.println("BAD REQUEST!");
            return ResponseEntity.badRequest().body("Bad request! " + e.toString());
        }
    }
    
    private boolean serviceNotExists(long id) {
        return (serviceRepository.findById(id) == null);
    }
    public void ServiceSanitization(Service service) {
        String allowed = "@._=-ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
        String ServiceEntered[][] = new String[3][2];
        ServiceEntered[0][0] = service.getName();
        ServiceEntered[1][0] = service.getDescription();
        ServiceEntered[2][0] = service.getDuration();
        ServiceEntered[3][0] = String.valueOf(service.getPrice());
        ServiceEntered[4][0] = String.valueOf(service.getFree_space());

        ServiceEntered[0][1] = "Name";
        ServiceEntered[1][1] = "Description";
        ServiceEntered[2][1] = "Duration";
        ServiceEntered[3][1] = "Price";
        ServiceEntered[4][1] = "Free space";


        for (int i = 0; i < ServiceEntered.length; i++) {
            if (!allowed.contains(ServiceEntered[i][0])) {
           //     errorInput += " Eroare  " + ServiceEntered[i][1];
            }
        }

    }
}