package com.assist.bookingjava.repositories;

import com.assist.bookingjava.model.Admins;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AdminRepository extends CrudRepository<Admins, Long> {
    List<Admins> findByIdAdmin(String idAdmin);
    List<Admins> findByName(String name);
    List<Admins> findByMail(String mail);
}
