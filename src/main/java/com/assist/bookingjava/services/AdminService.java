package com.assist.bookingjava.services;

import com.assist.bookingjava.services.interfaces.AdminInterface;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.assist.bookingjava.model.Admin;
import com.assist.bookingjava.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService implements AdminInterface {

    @Autowired
    private AdminRepository adminRepository;

    private static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();
    private String errorInput = "";

    public ResponseEntity findAllAdmins() {
        List<Admin> adminList = new ArrayList<>();

        for (Admin a : adminRepository.findAll()) {
            adminList.add(a);
        }

        return ResponseEntity.ok(adminList);
    }

    public ResponseEntity findAdminById(long id) {
        return ResponseEntity.ok(adminRepository.findOne(id));
    }


    public ResponseEntity findAdminByName(String name) {
        Admin admin = adminRepository.findByName(name);
        return ResponseEntity.ok(admin);
    }

    public ResponseEntity findAdminByEmail(@PathVariable String email) {
        Admin admin = adminRepository.findByEmail(email);
        return ResponseEntity.ok(admin);
    }

    public String bulkAddAdmin() {
        adminRepository.save(new Admin("andrei", "peter@assist.ro", encryptPassword("test")));
        adminRepository.save(new Admin("Andrews Stan", "astan@assist.ro", encryptPassword("#stan123")));
        adminRepository.save(new Admin("Kim II Smith", "kimii@assist.ro", encryptPassword("kim*i23")));
        adminRepository.save(new Admin("David Willie", "david@assist.ro", encryptPassword("david123")));
        adminRepository.save(new Admin("Peter Divide", "peter@assist.ro", encryptPassword("peter123")));
        return "Admin table was updated with five DEFAULT ROWS!";
    }

    public String editAdmin(Admin admin) {
        AdminSanitization(admin);
        if(errorInput!="")
        {
            return errorInput;
        }
        Admin tempAdmin = adminRepository.findByName(admin.getName());
        tempAdmin.setEmail(admin.getEmail());
        adminRepository.save(tempAdmin);
        return "PUT: Success!";

    }

    public String addAdmin(Admin admin) {


        AdminSanitization(admin);
        if(errorInput!="")
        {
            return errorInput;
        }
        String inputPass = admin.getPass();
        admin.setPass(encryptPassword(inputPass));
        adminRepository.save(admin);
        return "POST: Success!";
    }

    public String deleteAdmin(long id) {
        adminRepository.delete(id);
        return "DELETE: Success!";
    }


    private String encryptPassword(String inputPass) {
        return PASSWORD_ENCODER.encode(inputPass);
    }

    public void AdminSanitization(Admin admin) {
        String allowed = "@._=-ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
        String userEntered[][] = new String[3][2];
        userEntered[0][0] = admin.getName();
        userEntered[1][0] = admin.getPass();
        userEntered[2][0] = admin.getEmail();

        userEntered[0][1] = "Name";
        userEntered[1][1] = "Password";
        userEntered[2][1] = "Email";

        for (int i = 0; i < userEntered.length; i++) {
            if (!allowed.contains(userEntered[i][0])) {
                errorInput += "Eroare  " + userEntered[i][1];
            }
        }

    }
}