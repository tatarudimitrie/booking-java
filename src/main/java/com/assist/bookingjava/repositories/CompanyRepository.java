package com.assist.bookingjava.repositories;

import com.assist.bookingjava.model.Admin;
import com.assist.bookingjava.model.Company;
import org.springframework.data.repository.CrudRepository;

public interface CompanyRepository extends CrudRepository<Company, Long> {
    Company findById(long id);
    Company findByName(String name);
    Company findByAdmin(Admin admin);
}
