package com.assist.bookingjava.repositories;

import com.assist.bookingjava.model.Company;
import com.assist.bookingjava.model.Service;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface ServiceRepository extends CrudRepository<Service, Long> {
    Service findById(long id);
    List<Service> findByName(String name);
    List<Service> findByCompany(Company company);
}
