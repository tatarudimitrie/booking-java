package com.assist.bookingjava.services.interfaces;

import com.assist.bookingjava.model.Admin;
import com.assist.bookingjava.model.Company;
import org.springframework.http.ResponseEntity;

public interface CompanyInterface {

    ResponseEntity findAllCompanies();

    ResponseEntity findCompanyById(long id);

    ResponseEntity findCompanyByName(String name);

    ResponseEntity<String> editCompany(Company company);

    ResponseEntity<String> addCompany(Company company);

    ResponseEntity<String> deleteCompany(long id);

    ResponseEntity findByAdmin(Admin admin);

    String addBulkCompany();
}