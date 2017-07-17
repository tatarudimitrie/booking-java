package com.assist.bookingjava.repositories;

import com.assist.bookingjava.model.Admin;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface AdminRepository extends CrudRepository<Admin, Long> {
    Admin findById(String id);

    Admin findByName(String name);

    Admin findByEmail(String email);
}
