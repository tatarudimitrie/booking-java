package com.assist.bookingjava.services.interfaces;

import com.assist.bookingjava.model.Company;
import org.springframework.http.ResponseEntity;

public interface CompanyInterface {

    ResponseEntity findAllCompanies();

    ResponseEntity findCompanyById(long id);

    ResponseEntity findCompanyByName(String name);

    String addBulkCompany();

    String addCompany(Company company);

    String editCompany(Company company);

    String deleteCompany(long id);
}