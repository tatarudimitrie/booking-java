package com.assist.bookingjava.repositories;

import com.assist.bookingjava.model.Booking;
import com.assist.bookingjava.model.Service;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface BookingRepository extends CrudRepository<Booking, Long> {
    Booking findById(long id);
    List<Booking> findByName(String name);
    List<Booking> findByService(Service service);
}
