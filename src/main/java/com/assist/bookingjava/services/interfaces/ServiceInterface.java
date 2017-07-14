package com.assist.bookingjava.services.interfaces;

import com.assist.bookingjava.model.Service;
import org.springframework.http.ResponseEntity;

public interface ServiceInterface {

    ResponseEntity findAllServices();

    ResponseEntity findServiceById(long id);

    ResponseEntity findServiceByName(String name);

    String addBulkService();

    String addService(Service service);

    String editService(Service service);

    String deleteService(long id);
}
