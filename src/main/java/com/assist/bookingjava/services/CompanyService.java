package com.assist.bookingjava.services;

import com.assist.bookingjava.model.Admin;
import com.assist.bookingjava.model.Company;
import com.assist.bookingjava.repositories.AdminRepository;
import com.assist.bookingjava.repositories.CompanyRepository;
import com.assist.bookingjava.services.interfaces.CompanyInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class CompanyService implements CompanyInterface {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private AdminRepository adminRepository;

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
        companyRepository.save(new Company(870, "Assist", "Assist software", "C:/Assist.png"));
        companyRepository.save(new Company(871, "Google", "Google _search_", "C:/Google.png"));
        companyRepository.save(new Company(872, "PayPal", "PayPal _banking", "C:/PayPal.png"));
        companyRepository.save(new Company(873, "Amazon", "Amazon delivery", "C:/Amazon.png"));
        companyRepository.save(new Company(874, "GitHub", "GitHub headache", "C:/GitHub.png"));
        return "Company table was updated with five DEFAULT ROWS!";
    }

    public String addCompany(Company company) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Admin admin = adminRepository.findByName(authentication.getName());

        company.setAdmin(admin);
        companyRepository.save(company);
        return "POST: Success!" + company.toString();

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