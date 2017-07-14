package com.assist.bookingjava.services.interfaces;

import com.assist.bookingjava.model.Admin;
import org.springframework.http.ResponseEntity;

public interface AdminInterface {

    ResponseEntity findAllAdmins();

    ResponseEntity findAdminById(long id);

    ResponseEntity findAdminByName(String name);

    ResponseEntity findAdminByEmail(String email);

    String bulkAddAdmin();

    String editAdmin(Admin admin);

    String addAdmin(Admin admin);

    String deleteAdmin(long id);
}
