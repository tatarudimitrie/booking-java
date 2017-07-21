package com.assist.bookingjava.services;

import com.assist.bookingjava.model.Booking;
import com.assist.bookingjava.model.Schedule;
import com.assist.bookingjava.repositories.BookingRepository;
import com.assist.bookingjava.repositories.ScheduleRepository;
import com.assist.bookingjava.repositories.ServiceRepository;
import com.assist.bookingjava.services.interfaces.BookingInterface;
import org.hibernate.boot.jaxb.SourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class BookingService implements BookingInterface {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    private String errorBooking = "";
    private final static String emailAddress = "assistbooking7@gmail.com";
    private final static String emailPassword = "Assist2017";
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

        System.out.println("####################### " + service.getId() + " ################");

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
        List<Schedule> schedules = scheduleRepository.findByService(booking.getService());

        if (schedules == null) {
            return ResponseEntity.badRequest().body("BAD REQUEST! The service does not have any schedules!");
        }

        boolean findSchedule = false;

        for (Schedule s : schedules) {
            if(booking.getDate().equals(s.getTime())){
                findSchedule = true;
                break;
            }
        }

        //if(findSchedule) {
        //    return ResponseEntity.badRequest().body("The schedule is not available for the selected hour!");
        //}

        List<Booking> bookings = bookingRepository.findByService(booking.getService());

        try {
            for (Booking b : bookings) {
                if (b.getDate().equals(booking.getDate())) {
                    return ResponseEntity.badRequest().body("Booking exist for that date!");
                }
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("BAD REQUEST! " + e.toString());
        }

        try {
            com.assist.bookingjava.model.Service service = serviceRepository.findById(booking.getService().getId());
            booking.setService(service);
            bookingRepository.save(booking);
            System.out.println("Booking was added, for service: " + service.toString());
            sendBookingEmail(booking);
            return ResponseEntity.ok("Booking added successfully!");

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("BAD REQUEST! " + e.toString());
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

    private void sendBookingEmail(Booking booking) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        javax.mail.Session session = javax.mail.Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(emailAddress, emailPassword);
                    }
                });
        try {
            javax.mail.Message message = new MimeMessage(session);
            String email = booking.getEmail();
            message.setFrom(new InternetAddress(email));
            //message.setFrom(new InternetAddress("bolohan46@gmail.com"));
            message.setRecipients(javax.mail.Message.RecipientType.TO,
                    InternetAddress.parse("bolohan46@gmail.com"));
            message.setSubject("Confirm booking");
            message.setText("Succes.");
            message.setText("Your booking is: " + booking.getService().getName()+"\n"+
                    "     phone is: " + booking.getPhone()+"\n" +
                    "     email is: " + booking.getEmail()+"\n"+
                    "     date is: " + booking.getDate()+"\n"+
                    "     price is: " + booking.getService().getPrice()+"\n"+
                    "     company is: " + booking.getService().getCompany().getName()+"\n"+
                    " Thank you, "+booking.getName()+" because you chose us!");



            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
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
    public boolean validMail(String email){
        Pattern p = Pattern.compile("\\b[A-Za-z0-9._%-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}\\b");
        Matcher m = p.matcher(email);

        if (m.find()){
            return true;
        }else{
            return false;
        }

    }
    public boolean validPhone(String phone){
        Pattern pattern = Pattern.compile("\\d{10}");
        Matcher m = pattern.matcher(phone);

        if (m.find()){
            return true;
        }else{
            return false;
        }
    }
    public boolean validDate(String date){
        String exp="^[A-Z]{3}\\d{1}$";
        if(date.length()==3) {
            return true;
        }else{
            return false;}
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

        if(validMail(bookingEntered[2][0])==false){
            errorBooking+="Eroare"+bookingEntered[2][1];
        }
        if(validDate(bookingEntered[3][0])==false){
            errorBooking+="Eroare"+bookingEntered[3][1];
        }
        if(validPhone(bookingEntered[1][0])==false){
            errorBooking+="Eroare"+bookingEntered[1][1];
        }

        for (int i = 0; i < bookingEntered.length; i++) {
            if (!allowed.contains(bookingEntered[i][0])) {
           //     errorBooking += "Eroare  " + bookingEntered[i][1];
            }
        }

    }
}
