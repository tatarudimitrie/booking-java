/**
 * Created by myt on 12.07.2017.
 */

package com.assist.bookingjava.repositories;

import com.assist.bookingjava.model.Admins;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AdminRepository extends CrudRepository<Admins, Long> {
    List<Admins> findByLastName(String lastName);
}
