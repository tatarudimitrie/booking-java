package com.assist.bookingjava.services.interfaces;

import com.assist.bookingjava.model.Booking;
import org.springframework.http.ResponseEntity;

public interface BookingInterface {

    ResponseEntity findAllBookings();

    ResponseEntity findBookingById(long id);

    ResponseEntity findBookingByName(String name);

    String addBulkBooking();

    String addBooking(Booking booking);

    String editBooking(Booking booking);

    String deleteBooking(long id);
}