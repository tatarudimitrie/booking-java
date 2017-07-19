package com.assist.bookingjava.services.interfaces;

import com.assist.bookingjava.model.Booking;
import com.assist.bookingjava.model.Service;
import org.springframework.http.ResponseEntity;

public interface BookingInterface {

    ResponseEntity findAllBookings();

    ResponseEntity findBookingById(long id);

    ResponseEntity findBookingByName(String name);

    ResponseEntity findBookingByService(Service service);

    ResponseEntity<String> editBooking(Booking booking);

    ResponseEntity<String> addBooking(Booking booking);

    ResponseEntity<String> deleteBooking(long id);

    String addBulkBooking();
}