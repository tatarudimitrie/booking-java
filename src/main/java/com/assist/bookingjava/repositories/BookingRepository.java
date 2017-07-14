package com.assist.bookingjava.repositories;

import com.assist.bookingjava.model.Booking;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
public interface BookingRepository extends CrudRepository<Booking, Long> {
    List<Booking> findByName(String name);
}
