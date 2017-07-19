package com.assist.bookingjava.repositories;

import com.assist.bookingjava.model.Admin;
import com.assist.bookingjava.model.Company;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface CompanyRepository extends CrudRepository<Company, Long> {
    List<Company> findByName(String name);
    Company findByAdmin(Admin admin);
}
