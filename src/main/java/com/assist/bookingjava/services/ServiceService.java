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

        if(!isValidName(service.getName()))
        {

            System.out.println("BAD REQUEST NAME!");
            return ResponseEntity.badRequest().body("Bad request! invalid name");
        }

        if(!isvValidPrice(String.valueOf(service.getPrice())))
        {

            System.out.println("BAD REQUEST PHONE!");
            return ResponseEntity.badRequest().body("Bad request! invalid phone");
        }


        if(!isValidDuration(String.valueOf(service.getDuration())))
        {

            System.out.println("BAD REQUEST DURATION!");
            return ResponseEntity.badRequest().body("Bad request! invalid duration");
        }

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

    private boolean isValidName(String name){
        String expression="^[a-zA-Z0-9_]+( [a-zA-Z0-9_]+)*$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(name);

        return matcher.matches();
    }

    private boolean isvValidPrice(String number){
        String expression="^[0-9._]*$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(number);

        return matcher.matches();
    }

    private boolean isValidDuration(String number){
        String expression="^[0-9._]*$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(number);

        return matcher.matches();
    }


}