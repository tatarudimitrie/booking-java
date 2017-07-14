package com.assist.bookingjava.repositories;

import com.assist.bookingjava.model.Admin;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface AdminRepository extends CrudRepository<Admin, Long> {
    List<Admin> findById(String id);
    List<Admin> findByName(String name);
    List<Admin> findByEmail(String mail);

}
