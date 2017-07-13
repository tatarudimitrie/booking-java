package com.assist.bookingjava.repositories;


import com.assist.bookingjava.model.Company;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by cosmin on 12.07.2017.
 */
public interface CompanyRepository extends CrudRepository<Company, Long> {
    List<Company> findByLastName(String nameCompany);
}