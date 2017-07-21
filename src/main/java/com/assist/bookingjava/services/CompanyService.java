package com.assist.bookingjava.services;

import com.assist.bookingjava.model.Admin;
import com.assist.bookingjava.model.Company;
import com.assist.bookingjava.repositories.AdminRepository;
import com.assist.bookingjava.repositories.CompanyRepository;
import com.assist.bookingjava.services.interfaces.CompanyInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyService implements CompanyInterface {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private AdminRepository adminRepository;

    private String nl = "\n";

    public ResponseEntity findAllCompanies() {
        System.out.println(nl + "COMPANY GET: /companies/all");

        try {
            List<Company> companyList = new ArrayList<>();

            for (Company c : companyRepository.findAll()) {
                companyList.add(c);
            }

            System.out.println("OK: " + companyList.toString());
            return ResponseEntity.ok(companyList);
        } catch (Exception e) {
            System.out.println("BAD REQUEST!");
            return ResponseEntity.badRequest().body("Bad request! " + e.toString());
        }
    }

    public ResponseEntity findCompanyById(long id) {
        System.out.println(nl + "COMPANY GET: /companies/id/{" + id + "}");

        try {
            Company company = companyRepository.findOne(id);

            System.out.println("OK: " + company.toString());
            return ResponseEntity.ok(company);
        } catch (Exception e) {
            System.out.println("BAD REQUEST!");
            return ResponseEntity.badRequest().body("Bad request! " + e.toString());
        }
    }

    public ResponseEntity findCompanyByName(String name) {
        System.out.println(nl + "COMPANY GET: /companies/name/{" + name + "}");

        try {
            Company company = companyRepository.findByName(name);

            System.out.println("OK: " + company.toString());
            return ResponseEntity.ok(company);
        } catch (Exception e) {
            System.out.println("BAD REQUEST!");
            return ResponseEntity.badRequest().body("Bad request! " + e.toString());
        }
    }

    public ResponseEntity findCompanyByAdmin(Admin admin) {
        System.out.println(nl + "COMPANY (GET) POST: companies/admin for " + admin.toString());

        try {
            Admin currentAdmin = adminRepository.findByEmail(admin.getEmail());
            Company company = companyRepository.findByAdmin(currentAdmin);

            System.out.println("OK: " + company.toString());
            return ResponseEntity.ok(company);
        } catch (Exception e) {
            System.out.println("BAD REQUEST!");
            return ResponseEntity.badRequest().body("Bad request! " + e.toString());
        }
    }

    public ResponseEntity<String> editCompany(Company company) {
        System.out.println(nl + "COMPANY PUT: /companies/edit - for " + company.toString());

        if (isDuplicateName(company)) {
            System.out.println("BAD REQUEST! Duplicate name");
            return ResponseEntity.badRequest().body("Duplicate name");
        }

        try {
            Company currentCompany = companyRepository.findOne(company.getId());
            currentCompany.setName(company.getName());
            currentCompany.setDescription(company.getDescription());
            currentCompany.setImage_url(company.getImage_url());
            companyRepository.save(currentCompany);

            System.out.println("OK: " + company.toString());
            return ResponseEntity.ok("Company was successfully edited");
        } catch (Exception e) {
            System.out.println("BAD REQUEST!");
            return ResponseEntity.badRequest().body("Bad request! " + e.toString());
        }
    }

    public ResponseEntity<String> addCompany(Company company) {
        System.out.println(nl + "COMPANY POST: /companies/add - for " + company.toString());

        if (isDuplicateName(company)) {
            System.out.println("BAD REQUEST! Duplicate name");
            return ResponseEntity.badRequest().body("Duplicate name");
        }

        try {
            Admin admin = adminRepository.findByEmail(company.getAdmin().getEmail());
            company.setAdmin(admin);
            companyRepository.save(company);

            System.out.println("OK: " + company.toString() + " was added for" + admin.toString());
            return ResponseEntity.ok("Company added successfully!");
        } catch (Exception e) {
            System.out.println("BAD REQUEST!");
            return ResponseEntity.badRequest().body("Bad request! " + e.toString());
        }
    }

    public ResponseEntity<String> deleteCompany(long id) {
        System.out.println(nl + "COMPANY DELETE: /companies/delete - for company with id " + id );

        try {
            companyRepository.delete(id);

            System.out.println("OK: Deleted!");
            return ResponseEntity.ok("Company deleted successfully!");
        } catch (Exception e) {
            System.out.println("BAD REQUEST!");
            return ResponseEntity.badRequest().body("Bad request! " + e.toString());
        }
    }

    private boolean isDuplicateName(Company company) {
        Company currentCompany = companyRepository.findByName(company.getName());
        return (currentCompany.getId() != company.getId());
    }
}