package com.assist.bookingjava.services;

import com.assist.bookingjava.model.Company;
import com.assist.bookingjava.model.Service;
import com.assist.bookingjava.repositories.CompanyRepository;
import com.assist.bookingjava.repositories.ServiceRepository;
import com.assist.bookingjava.services.interfaces.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@org.springframework.stereotype.Service
public class ServiceService implements ServiceInterface {

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private CompanyRepository companyRepository;

    private String errorInput = "";

    public ResponseEntity findAllServices() {
        List<Service> serviceList = new ArrayList<>();

        try {
            for (Service s : serviceRepository.findAll()) {
                serviceList.add(s);
            }
            return ResponseEntity.ok(serviceList);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Bad request! " + e.toString());
        }
    }

    public ResponseEntity findServiceById(long id) {
        try {
            Service service = serviceRepository.findOne(id);
            return ResponseEntity.ok(service);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Bad request! " + e.toString());
        }
    }

    public ResponseEntity findServiceByName(String name) {
        try {
            List<Service> serviceList = serviceRepository.findByName(name);
            return ResponseEntity.ok(serviceList);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Bad request! " + e.toString());
        }
    }

    public ResponseEntity findServiceByCompany(Company company) {
        try {
            System.out.println(company.toString());
            Company currentCompany = companyRepository.findById(company.getId());
            List<Service> serviceList = serviceRepository.findByCompany(currentCompany);
            return ResponseEntity.ok(serviceList);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Bad request! " + e.toString());
        }
    }

    public ResponseEntity<String> editService(Service service) {
        try {
            Service currentService = serviceRepository.findOne(service.getId());
            currentService.setName(service.getName());
            currentService.setDescription(service.getDescription());
            currentService.setDuration(service.getDuration());
            currentService.setFree_space(service.getFree_space());
            currentService.setPrice(service.getPrice());
            currentService.setSchedule(service.getSchedule());
            serviceRepository.save(currentService);
            return ResponseEntity.ok("Service was successfully edited!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Bad request! " + e.toString());
        }
    }

    public ResponseEntity<String> addService(Service service) {

        try {
            Company company = companyRepository.findById(service.getCompany().getId());
            service.setCompany(company);
            serviceRepository.save(service);
            long id = service.getId();
            return ResponseEntity.ok(String.valueOf(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Bad request! " + e.toString());
        }
    }

    public ResponseEntity<String> deleteService(long id) {

        if (serviceNotExists(id)) {
            return ResponseEntity.badRequest().body("There is no service with id " + id + " in DB!");
        }

        try {
            serviceRepository.delete(id);
            return ResponseEntity.ok("Service with id " + id + " was successfully deleted!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Bad request! " + e.toString());
        }
    }

    public String addBulkService() {
        //serviceRepository.save(new Service(54, "Service1", "Description1", "1 (hour)",5, 200, "DD/MM/YYYY HH:mm"));
        //serviceRepository.save(new Service(55, "Service2", "Description2", "1 (hour)",4, 100, "DD/MM/YYYY HH:mm"));
        //serviceRepository.save(new Service(56, "Service3", "Description3", "1 (hour)",3, 200, "DD/MM/YYYY HH:mm"));
        //serviceRepository.save(new Service(57, "Service4", "Description4", "1 (hour)",4, 100, "DD/MM/YYYY HH:mm"));
        //serviceRepository.save(new Service(58, "Service5", "Description5", "1 (hour)",5, 200, "DD/MM/YYYY HH:mm"));
        return "Service table was updated with five DEFAULT ROWS!";
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
                errorInput += " Eroare  " + ServiceEntered[i][1];
            }
        }

    }
}