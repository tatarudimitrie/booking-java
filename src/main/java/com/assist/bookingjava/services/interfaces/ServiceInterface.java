package com.assist.bookingjava.services.interfaces;

import com.assist.bookingjava.model.Company;
import com.assist.bookingjava.model.Service;
import org.springframework.http.ResponseEntity;

public interface ServiceInterface {

    ResponseEntity findAllServices();

    ResponseEntity findServiceById(long id);

    ResponseEntity findServiceByName(String name);

    ResponseEntity findServiceByCompany(Company company);

    ResponseEntity<String> editService(Service service);

    ResponseEntity<String> addService(Service service);

    ResponseEntity<String> deleteService(long id);
}
