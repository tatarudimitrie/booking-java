package com.assist.bookingjava.services;

import com.assist.bookingjava.model.Booking;
import com.assist.bookingjava.repositories.BookingRepository;
import com.assist.bookingjava.services.interfaces.BookingInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class BookingService implements BookingInterface {

    @Autowired
    private BookingRepository bookingRepository;

    public ResponseEntity findAllBookings() {
        List<Booking> bookingList = new ArrayList<>();

        for(Booking b : bookingRepository.findAll()) {
            bookingList.add(b);
        }

        return ResponseEntity.ok(bookingList);
    }

    public ResponseEntity findBookingById(long id) {
        Booking booking = bookingRepository.findOne(id);
        return ResponseEntity.ok(booking);
    }

    public ResponseEntity findBookingByName(String name) {
        Collection<Booking> bookingList = new ArrayList<>();
        bookingList.addAll(bookingRepository.findByName(name));

        return ResponseEntity.ok(bookingList);
    }

    public String addBulkBooking() {
        bookingRepository.save(new Booking(30, "Name 1","Email1","Phone1","Date1"));
        bookingRepository.save(new Booking(31, "Name 2","Email2","Phone2","Date2"));
        bookingRepository.save(new Booking(32, "Name 3","Email3","Phone3","Date3"));
        bookingRepository.save(new Booking(33, "Name 4","Email4","Phone4","Date4"));
        bookingRepository.save(new Booking(34, "Name 5","Email5","Phone5","Date5"));
        return "Booking table was updated with five DEFAULT ROWS!";
    }

    public String addBooking(Booking booking) {
        bookingRepository.save(booking);
        return "POST: Success!";
    }

    public String editBooking(Booking booking) {
        bookingRepository.save(booking);
        return "PUT: Success!";
    }

    public String deleteBooking(long id) {
        bookingRepository.delete(id);
        return "DELETE: Success!";
    }
}
