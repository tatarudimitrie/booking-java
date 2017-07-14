package com.assist.bookingjava.controllers;

import com.assist.bookingjava.model.Booking;
import com.assist.bookingjava.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @RequestMapping(method= RequestMethod.GET, value="/bookings")
    public ResponseEntity findAllBookings(){
        return bookingService.findAllBookings();
    }

    @RequestMapping( method=RequestMethod.GET, value="/bookings/id/{id}")
    public ResponseEntity findBookingById(@PathVariable long id){
        return bookingService.findBookingById(id);
    }

    @RequestMapping(method=RequestMethod.GET, value="/bookings/name/{name}"  )
    public ResponseEntity findBookingByName(@PathVariable String name){
        return bookingService.findBookingByName(name);
    }

    @RequestMapping(method= RequestMethod.GET, value="/bookings/input")
    public String addBulkBooking(){
        return bookingService.addBulkBooking();
    }

    @RequestMapping(method=RequestMethod.POST, value="/bookings")
    public String addBooking(@RequestBody Booking booking) {
        return bookingService.addBooking(booking);
    }

    @RequestMapping(method=RequestMethod.PUT, value="/bookings")
    public void editBooking(@RequestBody Booking booking) {
        bookingService.editBooking(booking);
    }

    @RequestMapping(method=RequestMethod.DELETE, value="/bookings/{id}")
    public String deleteBooking(@PathVariable long id) {
        return bookingService.deleteBooking(id);
    }
}
