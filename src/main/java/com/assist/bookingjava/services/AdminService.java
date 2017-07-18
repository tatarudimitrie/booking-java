package com.assist.bookingjava.services;

import com.assist.bookingjava.services.interfaces.AdminInterface;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
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

    public ResponseEntity findAdminByName(String name){
        Admin admin = adminRepository.findByName(name);
        return ResponseEntity.ok(admin);
    }

    public boolean findAdminByNameAndPass(String name, String pass){
        System.out.println("Input pass: " + pass);
        System.out.println("Encrypted pass: " + encryptPassword(pass));

        Admin admin = adminRepository.findByName(name);
        System.out.println(admin.toString());
        return admin.getPass().equals(encryptPassword(pass));
    }

    public ResponseEntity findAdminByLogin(Admin admin){
        Admin adminTemp = adminRepository.findByEmail(admin.getEmail());
        if (adminTemp == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        boolean status = PASSWORD_ENCODER.matches(admin.getPass(), adminTemp.getPass());
        return status? new ResponseEntity(HttpStatus.OK) : new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity findAdminByEmail(String email){
        Admin admin = adminRepository.findByEmail(email);
        return ResponseEntity.ok(admin);
    }

    public String bulkAddAdmin() {
        adminRepository.save(new Admin("andrei", "peter@assist.ro", encryptPassword("test")));
        adminRepository.save(new Admin("Andrews Stan", "astan@assist.ro", encryptPassword("stan1234")));
        adminRepository.save(new Admin("Kim II Smith", "kimii@assist.ro", encryptPassword("kim*i23")));
        adminRepository.save(new Admin("David Willie", "david@assist.ro", encryptPassword("david123")));
        adminRepository.save(new Admin("Peter Divide", "peter@assist.ro", encryptPassword("peter123")));
        return "Admin table was updated with five DEFAULT ROWS!";
    }

    public String editAdmin(Admin admin) {

        Admin tempAdmin = adminRepository.findByName(admin.getName());
        tempAdmin.setEmail(admin.getEmail());
        adminRepository.save(tempAdmin);
        return "PUT: Success!";
        //TODO PASS ENCRYPT
        //TODO CHECK PASS LENGTH
    }

    public String addAdmin(Admin admin) {

        //ResponseEntity<>

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
}