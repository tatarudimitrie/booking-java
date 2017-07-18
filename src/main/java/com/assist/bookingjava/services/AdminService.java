package com.assist.bookingjava.services;

import com.assist.bookingjava.services.interfaces.AdminInterface;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.assist.bookingjava.model.Admin;
import com.assist.bookingjava.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

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
            a.setPass("*");
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
        //adminSanitize(admin);
        Admin tempAdmin = adminRepository.findByName(admin.getName());
        tempAdmin.setEmail(admin.getEmail());
        try
        {
            adminRepository.save(tempAdmin);
        }
        catch (Exception e)
        {
            return  "Eroare la salvare "+e.toString();
        }

        return "PUT: Success!";

    }

    public ResponseEntity<String> addAdmin(Admin admin) {

        System.out.println("ADD ADMIN --> " + admin.toString());

        String sanitize = adminSanitize(admin);
        if (!sanitize.equals("")) {
            //return ResponseEntity.badRequest().body("Wrong input!\n" + sanitize);
        }

        if (isDuplicateName(admin.getName())) {
            return ResponseEntity.badRequest().body("Duplicate name");
        }

        if (isDuplicateEmail(admin.getEmail())) {
            return ResponseEntity.badRequest().body("Duplicate email");
        }

        if (isInvalidPass(admin.getPass())) {
            return ResponseEntity.badRequest().body("Password length must be between 6..16");
        }

        try {
            admin.setPass(encryptPassword(admin.getPass()));
            adminRepository.save(admin);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Something happened!");
        }

        return ResponseEntity.ok("Admin registered successfully!");
    }

    public String deleteAdmin(long id) {
        adminRepository.delete(id);
        return "DELETE: Success!";
    }

    /* ADMIN INPUT CHECKS */
    private boolean isDuplicateName(String name) {
        return (adminRepository.findByName(name) != null);
    }

    private boolean isDuplicateEmail(String email) {
        return (adminRepository.findByEmail(email) != null);
    }

    private boolean isInvalidPass(String pass) {
        return !((pass.length() >= 6) && (pass.length() <= 16));
    }

    private String encryptPassword(String inputPass) {
        return PASSWORD_ENCODER.encode(inputPass);
    }

    private String adminSanitize(Admin admin) {
        String allowed = "@._=-ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
        String userEntered[][] = new String[3][2];
        userEntered[0][0] = admin.getName();
        userEntered[1][0] = admin.getPass();
        userEntered[2][0] = admin.getEmail();

        userEntered[0][1] = "Name";
        userEntered[1][1] = "Password";
        userEntered[2][1] = "Email";

        String errorString = "";

        for (int i = 0; i < userEntered.length; ++i) {
            if (!allowed.contains(userEntered[i][0])) {
                errorString += "Error  " + userEntered[i][1] + " ";
            }
        }

        return errorString;
    }
}