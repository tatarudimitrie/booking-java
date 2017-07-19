package com.assist.bookingjava.services;

import com.assist.bookingjava.model.Booking;
import com.assist.bookingjava.repositories.BookingRepository;
import com.assist.bookingjava.repositories.ServiceRepository;
import com.assist.bookingjava.services.interfaces.BookingInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookingService implements BookingInterface {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    private String errorBooking = "";

    public ResponseEntity findAllBookings() {
        try {
            List<Booking> bookingList = new ArrayList<>();
            for(Booking b : bookingRepository.findAll()) {
                bookingList.add(b);
            }
            return ResponseEntity.ok(bookingList);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Bad request! " + e.toString());
        }
    }

    public ResponseEntity findBookingById(long id) {
        try {
            Booking booking = bookingRepository.findOne(id);
            return ResponseEntity.ok(booking);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Bad request! " + e.toString());
        }
    }

    public ResponseEntity findBookingByName(String name) {
        try {
            List<Booking> bookingList = bookingRepository.findByName(name);
            return ResponseEntity.ok(bookingList);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Bad request! " + e.toString());
        }
    }

    public ResponseEntity findBookingByService(com.assist.bookingjava.model.Service service) {
        try {
            System.out.println(service.toString());
            com.assist.bookingjava.model.Service currentService = serviceRepository.findById(service.getId());
            List<Booking> bookingList = bookingRepository.findByService(currentService);
            return ResponseEntity.ok(bookingList);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Bad request! " + e.toString());
        }
    }

    public ResponseEntity<String> editBooking(Booking booking) {
        //BookingSanitization(booking);

        try {
            Booking currentBooking = bookingRepository.findOne(booking.getId());
            currentBooking.setName(booking.getName());
            currentBooking.setEmail(booking.getEmail());
            currentBooking.setPhone(booking.getPhone());
            currentBooking.setDate(booking.getDate());
            bookingRepository.save(currentBooking);
            return ResponseEntity.ok("Booking was successfully edited!");
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body("Bad request! " + e.toString());
        }
    }

    public ResponseEntity<String> addBooking(Booking booking) {
        // BookingSanitization(booking);

        try {
            com.assist.bookingjava.model.Service service = serviceRepository.findById(booking.getService().getId());
            booking.setService(service);
            bookingRepository.save(booking);
            System.out.println("Booking was added, for service: " + service.toString());
            return ResponseEntity.ok("Booking added successfully!");
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body("Bad request! " + e.toString());
        }
    }

    public ResponseEntity<String> deleteBooking(long id) {

        if (bookingNotExists(id)) {
            return ResponseEntity.badRequest().body("There is no booking with id " + id + " in DB!");
        }

        try {
            serviceRepository.delete(id);
            return ResponseEntity.ok("Booking with id " + id + " was successfully deleted!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Bad request! " + e.toString());
        }
    }

    public String addBulkBooking() {
        bookingRepository.save(new Booking(30, "Name 1","Email1","Phone1","Date1"));
        bookingRepository.save(new Booking(31, "Name 2","Email2","Phone2","Date2"));
        bookingRepository.save(new Booking(32, "Name 3","Email3","Phone3","Date3"));
        bookingRepository.save(new Booking(33, "Name 4","Email4","Phone4","Date4"));
        bookingRepository.save(new Booking(34, "Name 5","Email5","Phone5","Date5"));
        return "Booking table was updated with five DEFAULT ROWS!";
    }

    private boolean bookingNotExists(long id) {
        return (bookingRepository.findById(id) == null);
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
