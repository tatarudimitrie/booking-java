package com.assist.bookingjava.controllers;

import com.assist.bookingjava.model.Booking;
import com.assist.bookingjava.model.Service;
import com.assist.bookingjava.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @RequestMapping(method= RequestMethod.GET, value="/bookings/all")
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

    @RequestMapping(method = RequestMethod.POST, value = "/bookings/service")
    public ResponseEntity findBookingByService(@RequestBody Service service){
        return bookingService.findBookingByService(service);
    }

    @RequestMapping(method=RequestMethod.PUT, value="/bookings/edit")
    public ResponseEntity<String> editBooking(@RequestBody Booking booking) {
        return bookingService.editBooking(booking);
    }

    @RequestMapping(method=RequestMethod.POST, value="/bookings/add")
    public ResponseEntity<String> addBooking(@RequestBody Booking booking) {
        return bookingService.addBooking(booking);
    }

    @RequestMapping(method=RequestMethod.DELETE, value="/bookings/delete/{id}")
    public ResponseEntity<String> deleteBooking(@PathVariable long id) {
        return bookingService.deleteBooking(id);
    }

    @RequestMapping(method= RequestMethod.GET, value="/bookings/input")
    public String addBulkBooking(){
        return bookingService.addBulkBooking();
    }
}