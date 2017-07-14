package com.assist.bookingjava.services;

import com.assist.bookingjava.model.Admin;
import com.assist.bookingjava.repositories.AdminRepository;
import com.assist.bookingjava.services.interfaces.AdminInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class AdminService implements AdminInterface{

    @Autowired
    private AdminRepository adminRepository;

    public ResponseEntity findAllAdmins(){
        List<Admin> adminList = new ArrayList<>();

        for(Admin a : adminRepository.findAll()) {
            adminList.add(a);
        }

        return ResponseEntity.ok(adminList);
    }

    public ResponseEntity findAdminById(long id) {
        return ResponseEntity.ok(adminRepository.findOne(id));
    }

    public ResponseEntity findAdminByName(String name){
        Collection<Admin> adminList = new ArrayList<>();
        adminList.addAll(adminRepository.findByName(name));

        return ResponseEntity.ok(adminList);
    }

    public ResponseEntity findAdminByEmail(@PathVariable String email){
        Collection<Admin> adminList = new ArrayList<>();
        adminList.addAll(adminRepository.findByName(email));

        return ResponseEntity.ok(adminList);
    }

    public String bulkAddAdmin() {
        adminRepository.save(new Admin("Peter George", "peter@assist.ro", "peter"));
        adminRepository.save(new Admin("Andrews Stan", "astan@assist.ro", "#stan"));
        adminRepository.save(new Admin("Kim II Smith", "kimii@assist.ro", "kim*i"));
        adminRepository.save(new Admin("David Willie", "david@assist.ro", "david"));
        adminRepository.save(new Admin("Peter Divide", "peter@assist.ro", "peter"));
        return "Admin table was updated with five DEFAULT ROWS!";
    }

    public String editAdmin(Admin admin) {

        if (adminRepository.findByEmail(admin.getEmail()).isEmpty()) {
            adminRepository.save(admin);
            return "PUT: Success!";
        } else {
            return "Error! Duplicate email";
        }
        //TODO PASS ENCRYPT
        //TODO CHECK PASS LENGTH
    }

    public String addAdmin(Admin admin) {
        adminRepository.save(admin);
        return "POST: Success!";
    }

    public String deleteAdmin(long id) {
        adminRepository.delete(id);
        return "DELETE: Success!";
    }
}




                /*"....._......_______..._______..._   _______   _______ " + "<br/>" +
                "    #@#    |*#*#*#*| |*#*#*#*| |*| |*#*#*#*| |*#*#*#*|" + "<br/>" +
                "  *|---|#  |#* ----  |#* ----  |#| |#* ----  '-- * --'" + "<br/>" +
                " |#|   |*| |*#|      |*#|      |*| |*#|         |#|   " + "<br/>" +
                " |*|   |#| |#* ----  |#* ----  |#| |#* ----     |#|   " + "<br/>" +
                " |#|___|*| |*#*#*#*| |*#*#*#*| |*| |*#*#*#*|    |#|   " + "<br/>" +
                " |*#*@#*#| '---- *#| '---- *#| |#| '---- *#|    |#|   " + "<br/>" +
                " |#|---|*|      |#*|      |#*| |*|      |#*|    |#|   " + "<br/>" +
                " |*|   |#|  ----'*#|  ----'*#| |#|  ----'*#|    |#|   " + "<br/>" +
                " |#|   |*| |*#*#*#*| |*#*#*#*| |*| |*#*#*#*|    |#|   " + "<br/>" +
                " |#|   |*| '_______' '_______'  _  '_______'    |#|   #ASSIST.RO";*/