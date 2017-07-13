package com.assist.bookingjava.repositories;

import com.assist.bookingjava.model.Admin;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

<<<<<<< Updated upstream




public interface AdminRepository extends CrudRepository<Admin, Long> {
    List<Admin> findByIdAdmin(String idAdmin);

    List<Admin> findByName(String name);

    List<Admin> findByMail(String mail);

=======
public interface AdminRepository extends CrudRepository<Admin, Long> {
    List<Admin> findByLastName(String lastName);
>>>>>>> Stashed changes
}

