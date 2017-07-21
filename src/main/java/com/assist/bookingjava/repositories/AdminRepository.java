package com.assist.bookingjava.repositories;

import com.assist.bookingjava.model.Admin;
import org.springframework.data.repository.CrudRepository;

public interface AdminRepository extends CrudRepository<Admin, Long> {
    Admin findByName(String name);
    Admin findByEmail(String email);
}
