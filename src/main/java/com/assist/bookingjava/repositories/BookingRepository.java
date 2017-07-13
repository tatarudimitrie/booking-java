package com.assist.bookingjava.repositories;

import com.assist.bookingjava.model.Booking;
import com.assist.bookingjava.model.Company;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by root on 13.07.2017.
 */
public interface BookingRepository extends CrudRepository<Booking, Long> {
    List<Booking> findByName(String name);
}
