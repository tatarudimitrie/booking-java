package com.assist.bookingjava.services.interfaces;

import com.assist.bookingjava.model.Admin;
import org.springframework.http.ResponseEntity;

public interface AdminInterface {

    ResponseEntity findAllAdmins();

    ResponseEntity findAdminById(long id);

    ResponseEntity findAdminByName(String name);

    ResponseEntity<String> addAdmin(Admin admin);

    ResponseEntity<String> editAdmin(Admin admin);

    ResponseEntity<String> deleteAdmin(long id);

    String bulkAddAdmin();
}
