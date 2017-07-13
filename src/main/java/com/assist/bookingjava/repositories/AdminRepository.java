/**
 * Created by myt on 12.07.2017.
 */

package com.assist.bookingjava.repositories;

import com.assist.bookingjava.model.Admin;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DataRepository extends CrudRepository<Admin, Long> {
    List<Admin> findByLastName(String lastName);
}
