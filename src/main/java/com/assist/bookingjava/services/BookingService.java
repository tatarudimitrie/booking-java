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


    private String errorBooking = "";

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
        BookingSanitization(booking);
        if(errorBooking!="")
        {
            return errorBooking;
        }
        try {
            bookingRepository.save(booking);
        }
        catch (Exception e)
        {
            return "Eroare la salvare " + e.getMessage();
        }
        return "POST: Success!";
    }

    public String editBooking(Booking booking) {
        BookingSanitization(booking);
        if(errorBooking!="")
        {
            return errorBooking;
        }
        try {
            bookingRepository.save(booking);
        }
        catch (Exception e)
        {
            return "Eroare la salvare " + e.getMessage();
        }
        return "PUT: Success!";
    }

    public String deleteBooking(long id) {
        bookingRepository.delete(id);
        return "DELETE: Success!";
    }
    public void BookingSanitization(Booking booking) {
        String allowed = "@._=-ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
        String bookingEntered[][] = new String[3][2];
        bookingEntered[0][0] = booking.getName();
        bookingEntered[1][0] = booking.getPhone();
        bookingEntered[2][0] = booking.getEmail();
        bookingEntered[3][0] = booking.getDate();

        bookingEntered[0][1] = "Name";
        bookingEntered[1][1] = "Phone";
        bookingEntered[2][1] = "Email";
        bookingEntered[3][1] = "Date";


        for (int i = 0; i < bookingEntered.length; i++) {
            if (!allowed.contains(bookingEntered[i][0])) {
                errorBooking += "Eroare  " + bookingEntered[i][1];
            }
        }

    }
}
