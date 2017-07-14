package com.assist.bookingjava.services;

import com.assist.bookingjava.model.Company;
import com.assist.bookingjava.repositories.CompanyRepository;
import com.assist.bookingjava.services.interfaces.CompanyInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class CompanyService implements CompanyInterface {

    @Autowired
    private CompanyRepository companyRepository;

    public ResponseEntity findAllCompanies() {
        List<Company> companyList = new ArrayList<>();

        for(Company c : companyRepository.findAll()) {
            companyList.add(c);
        }

        return ResponseEntity.ok(companyList);
    }

    public ResponseEntity findCompanyById(long id) {
        Company company = companyRepository.findOne(id);
        return ResponseEntity.ok(company);
    }

    public ResponseEntity findCompanyByName(String name) {
        Collection<Company> companyList = new ArrayList<>();
        companyList.addAll(companyRepository.findByName(name));

        return ResponseEntity.ok(companyList);
    }

    public String addBulkCompany() {
        companyRepository.save(new Company("Assist", "Assist software", "C:/Assist.png"));
        companyRepository.save(new Company("Google", "Google _search_", "C:/Google.png"));
        companyRepository.save(new Company("PayPal", "PayPal _banking", "C:/PayPal.png"));
        companyRepository.save(new Company("Amazon", "Amazon delivery", "C:/Amazon.png"));
        companyRepository.save(new Company("GitHub", "GitHub headache", "C:/GitHub.png"));
        return "Company table was updated with five DEFAULT ROWS!";
    }

    public String addCompany(Company company) {
        companyRepository.save(company);
        return "POST: Success!";
    }

    public String editCompany(Company company) {
        companyRepository.save(company);
        return "PUT: Success!";
    }

    public String deleteCompany(long id) {
        companyRepository.delete(id);
        return "DELETE: Success!";
    }
}