package com.assist.bookingjava.repositories;


import com.assist.bookingjava.model.Company;
import com.assist.bookingjava.services.interfaces.AdminInterface;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by cosmin on 12.07.2017.
 */
public interface CompanyRepository extends CrudRepository<Company, Long> {
    List<Company> findByNameCompany(String nameCompany);

    /**
     * Created by myt on 13.07.2017.
     */

    @Service
    class AdminService implements AdminInterface {
    }
}