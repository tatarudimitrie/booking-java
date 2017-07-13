package com.assist.bookingjava.repositories;

<<<<<<< Updated upstream


import com.assist.bookingjava.model.Company;
import org.springframework.data.repository.CrudRepository;

=======
import com.assist.bookingjava.model.Admin;
import com.assist.bookingjava.model.Company;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.Table;
>>>>>>> Stashed changes
import java.util.List;

/**
 * Created by cosmin on 12.07.2017.
 */
<<<<<<< Updated upstream
public interface CompanyRepository extends CrudRepository<Company, Long> {
    List<Company> findByNameCompany(String nameCompany);
=======
@Table(name="CompanyRepository")
public interface CompanyRepository extends CrudRepository<Company, Long> {
    List<Admin> findByidCompany(String idCompany);
>>>>>>> Stashed changes
}

