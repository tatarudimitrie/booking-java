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

    public ResponseEntity findAllCompanies() {
        List<Company> companyList = new ArrayList<>();

        try {
            for (Company c : companyRepository.findAll()) {
                companyList.add(c);
            }
            return ResponseEntity.ok(companyList);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Bad request! " + e.toString());
        }
    }

    public ResponseEntity findCompanyById(long id) {
        try {
            Company company = companyRepository.findOne(id);
            return ResponseEntity.ok(company);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Bad request! " + e.toString());
        }
    }

    public ResponseEntity findCompanyByName(String name) {
        try {
            Company company = companyRepository.findByName(name);
            return ResponseEntity.ok(company);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Bad request! " + e.toString());
        }
    }

    public ResponseEntity findCompanyByAdmin(Admin admin) {
        System.out.println("Requested company for admin: " + admin.toString());
        Admin currentAdmin = adminRepository.findByEmail(admin.getEmail());
        Company company = companyRepository.findByAdmin(currentAdmin);
        if (company == null) {
            System.out.println("BAD REQUEST!");
            return ResponseEntity.badRequest().body("The admin with email " +
                    admin.getEmail() + " does not have a company!");
        }

        System.out.println("SENT: " + company.toString());
        return ResponseEntity.ok(company);
    }

    public ResponseEntity<String> editCompany(Company company) {

        if (isDuplicateName(company)) {
            return ResponseEntity.badRequest().body("Duplicate name");
        }

        try {
            Company currentCompany = companyRepository.findOne(company.getId());
            currentCompany.setName(company.getName());
            currentCompany.setDescription(company.getDescription());
            currentCompany.setImage_url(company.getImage_url());
            companyRepository.save(currentCompany);
            return ResponseEntity.ok("Company was successfully edited");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Bad request! " + e.toString());
        }
    }

    public ResponseEntity<String> addCompany(Company company) {
        try {
            Admin admin = adminRepository.findByEmail(company.getAdmin().getEmail());
            company.setAdmin(admin);
            companyRepository.save(company);
            System.out.println("Company was added, for admin: " + admin.toString());
            return ResponseEntity.ok("Company added successfully!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Bad request! " + e.toString());
        }
    }

    public ResponseEntity<String> deleteCompany(long id) {
        try {
            companyRepository.delete(id);
            return ResponseEntity.ok("Company deleted successfully!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Bad request! " + e.toString());
        }
    }

    public String addBulkCompany() {
        companyRepository.save(new Company("peter@assist.ro", "Assist", "Assist software", "C:/Assist.png"));
        companyRepository.save(new Company("astan@assist.ro", "Google", "Google _search_", "C:/Google.png"));
        companyRepository.save(new Company("kimii@assist.ro", "PayPal", "PayPal _banking", "C:/PayPal.png"));
        companyRepository.save(new Company("david@assist.ro", "Amazon", "Amazon delivery", "C:/Amazon.png"));
        companyRepository.save(new Company("mihai?@mail.ro", "GitHub", "GitHub headache", "C:/GitHub.png"));
        return "Company table was updated with five DEFAULT ROWS!";
    }

    private boolean isDuplicateName(Company company) {
        Company currentCompany = companyRepository.findByName(company.getName());
        return (currentCompany.getId() != company.getId());
    }
}