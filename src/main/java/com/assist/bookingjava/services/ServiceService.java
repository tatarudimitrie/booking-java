package com.assist.bookingjava.services;

import com.assist.bookingjava.model.Service;
import com.assist.bookingjava.repositories.ServiceRepository;
import com.assist.bookingjava.services.interfaces.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@org.springframework.stereotype.Service
public class ServiceService implements ServiceInterface {

    @Autowired
    private ServiceRepository serviceRepository;

    private String errorInput = "";

    public ResponseEntity findAllServices() {
        List<Service> serviceList = new ArrayList<>();

        for(Service s : serviceRepository.findAll()) {
            serviceList.add(s);
        }

        return ResponseEntity.ok(serviceList);
    }

    public ResponseEntity findServiceById(long id) {
        Service service = serviceRepository.findOne(id);
        return ResponseEntity.ok(service);
    }

    public ResponseEntity findServiceByName(String name) {
        Collection<Service> serviceList = new ArrayList<>();
        serviceList.addAll(serviceRepository.findByName(name));

        return ResponseEntity.ok(serviceList);
    }

    public String addBulkService() {
        serviceRepository.save(new Service(54, "Service1", "Description1", "1 (hour)",5, 200, "DD/MM/YYYY HH:mm"));
        serviceRepository.save(new Service(55, "Service2", "Description2", "1 (hour)",4, 100, "DD/MM/YYYY HH:mm"));
        serviceRepository.save(new Service(56, "Service3", "Description3", "1 (hour)",3, 200, "DD/MM/YYYY HH:mm"));
        serviceRepository.save(new Service(57, "Service4", "Description4", "1 (hour)",4, 100, "DD/MM/YYYY HH:mm"));
        serviceRepository.save(new Service(58, "Service5", "Description5", "1 (hour)",5, 200, "DD/MM/YYYY HH:mm"));
        return "Service table was updated with five DEFAULT ROWS!";
    }

    public String addService(Service service) {
        serviceRepository.save(service);
        return "POST: Success!";
    }

    public String editService(Service service) {
        serviceRepository.save(service);
        return "PUT: Success!";
    }

    public String deleteService(long id) {
        serviceRepository.delete(id);
        return "DELETE: Success!";
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