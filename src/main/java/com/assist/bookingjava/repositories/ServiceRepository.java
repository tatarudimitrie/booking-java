package com.assist.bookingjava.repositories;

import com.assist.bookingjava.model.Service;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface ServiceRepository extends CrudRepository<Service, Long> {
    List<Service> findByName(String name);
}
