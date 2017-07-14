package com.assist.bookingjava.repositories;

import com.assist.bookingjava.model.Service;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.Table;
import java.util.List;

/**
 * Created by root on 13.07.2017.
 */
@Table(name="ServiceRepository")
public interface ServiceRepository extends CrudRepository<Service, Long> {

    List<Service> findByName(String name);
}
