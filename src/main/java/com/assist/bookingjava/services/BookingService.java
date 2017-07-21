package com.assist.bookingjava.services;

import com.assist.bookingjava.model.Booking;
import com.assist.bookingjava.model.Company;
import com.assist.bookingjava.model.Schedule;
import com.assist.bookingjava.model.Service;
import com.assist.bookingjava.repositories.BookingRepository;
import com.assist.bookingjava.repositories.CompanyRepository;
import com.assist.bookingjava.repositories.ScheduleRepository;
import com.assist.bookingjava.repositories.ServiceRepository;
import com.assist.bookingjava.services.interfaces.BookingInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

@org.springframework.stereotype.Service
public class BookingService implements BookingInterface {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private CompanyRepository companyRepository;

    private String nl = "\n";
    private String errorBooking = "";
    private final static String emailAddress = "assistbooking7@gmail.com";
    private final static String emailPassword = "Assist2017";

    public ResponseEntity findAllBookings() {
        System.out.println(nl + "BOOKING GET: /bookings" +
                "/all");
        try {
            List<Booking> bookingList = new ArrayList<>();
            for(Booking b : bookingRepository.findAll()) {
                bookingList.add(b);
            }

            System.out.println("OK: " + bookingList.toString());
            return ResponseEntity.ok(bookingList);
        } catch (Exception e) {
            System.out.println("BAD REQUEST!");
            return ResponseEntity.badRequest().body("Bad request! " + e.toString());
        }
    }

    public ResponseEntity findBookingById(long id) {
        System.out.println(nl + "BOOKING GET: /bookings/id/{" + id + "}");

        try {
            Booking booking = bookingRepository.findOne(id);

            System.out.println("OK: " + booking.toString());
            return ResponseEntity.ok(booking);
        } catch (Exception e) {
            System.out.println("BAD REQUEST!");
            return ResponseEntity.badRequest().body("Bad request! " + e.toString());
        }
    }

    public ResponseEntity findBookingByName(String name) {
        System.out.println(nl + "SCHEDULE GET: /schedules/time/{" + name + "}");

        try {
            List<Booking> bookingList = bookingRepository.findByName(name);

            System.out.println("OK: " + bookingList.toString());
            return ResponseEntity.ok(bookingList);
        } catch (Exception e) {
            System.out.println("BAD REQUEST!");
            return ResponseEntity.badRequest().body("Bad request! " + e.toString());
        }
    }

    public ResponseEntity findBookingByCompany(long id) {
        System.out.println(nl + "BOOKING GET: /bookings/company/{" + id + "}");

        try {
            Company currentCompany = companyRepository.findById(id);
            List<Service> serviceList = serviceRepository.findByCompany(currentCompany);

            List<Booking> bookingList = new ArrayList<>();

            for (Service s : serviceList) {
                List<Booking> tempList = bookingRepository.findByService(s);
                bookingList.addAll(tempList);
            }

            System.out.println("OK: " + bookingList.toString());
            return ResponseEntity.ok(bookingList);
        } catch (Exception e) {
            System.out.println("BAD REQUEST!");
            return ResponseEntity.badRequest().body("Bad request! " + e.toString());
        }
    }

    public ResponseEntity findBookingByService(Service service) {
        System.out.println(nl + "BOOKING (GET) POST: /bookings/service/ for " + service.toString());

        try {
            Service currentService = serviceRepository.findById(service.getId());
            List<Booking> bookingList = bookingRepository.findByService(currentService);

            System.out.println("OK: " + bookingList.toString());
            return ResponseEntity.ok(bookingList);
        } catch (Exception e) {
            System.out.println("BAD REQUEST!");
            return ResponseEntity.badRequest().body("Bad request! " + e.toString());
        }
    }

    public ResponseEntity<String> addBooking(Booking booking) {
        System.out.println(nl + "BOOKING  POST: /bookings/add/ for " + booking.toString());

        BookingSanitization(booking);
        if(errorBooking!="")
          {
             System.out.println(errorBooking);
             return ResponseEntity.badRequest().body("BAD REQUEST! " + errorBooking);
          }
        List<Schedule> schedules=new ArrayList<>();
        schedules = scheduleRepository.findByService(booking.getService());
        boolean canSchedule = false;

        if (schedules == null) {
            System.out.println("BAD REQUEST! The service does not have any schedules!");
            return ResponseEntity.badRequest().body("BAD REQUEST! The service does not have any schedules!");
        }

        for (Schedule s : schedules) {
            if(booking.getDate().equals(s.getTime())){
                canSchedule = true;
            }
        }

        if (!canSchedule) {
            System.out.println("BAD REQUEST! Can't book at this hour!");
            return ResponseEntity.badRequest().body("BAD REQUEST! Can't book at this hour!");
        }

        List<Booking> bookings = bookingRepository.findByService(booking.getService());

        canSchedule = true;

        for (Booking b : bookings) {
            if (b.getDate().equals(booking.getDate())) {
                canSchedule = false;
            }
        }

        if (!canSchedule) {
            System.out.println("BAD REQUEST! Booking exists at that date!");
            return ResponseEntity.badRequest().body("BAD REQUEST! Booking exists at that date!");
        }

        try {
            Service service = serviceRepository.findById(booking.getService().getId());
            booking.setService(service);
            bookingRepository.save(booking);
            sendBookingEmail(booking);

            System.out.println("OK: " + booking.toString());
            return ResponseEntity.ok("Booking added successfully!");
        } catch (Exception e) {
            System.out.println("BAD REQUEST!");
            return ResponseEntity.badRequest().body("BAD REQUEST! " + e.toString());
        }
    }

    public ResponseEntity<String> editBooking(Booking booking) {
        System.out.println(nl + "BOOKING PUT: /bookings/edit/ for " + booking.toString());

        try {
            Booking currentBooking = bookingRepository.findOne(booking.getId());
            currentBooking.setName(booking.getName());
            currentBooking.setEmail(booking.getEmail());
            currentBooking.setPhone(booking.getPhone());
            currentBooking.setDate(booking.getDate());
            bookingRepository.save(currentBooking);

            System.out.println("OK: " + currentBooking.toString());
            return ResponseEntity.ok("Booking was successfully edited!");
        } catch (Exception e) {
            System.out.println("BAD REQUEST!");
            return ResponseEntity.badRequest().body("Bad request! " + e.toString());
        }
    }

    public ResponseEntity<String> deleteBooking(long id) {
        System.out.println(nl + "BOOKING DELETE: /bookings/delete/{" + id + "}");

        if (bookingNotExists(id)) {
            System.out.println(nl + "BAD REQUEST: No booking with id " + id + "in DB!");
            return ResponseEntity.badRequest().body("BAD REQUEST: No booking with id " + id + "in DB!");
        }

        try {
            serviceRepository.delete(id);

            System.out.println("OK: Deleted successfully!");
            return ResponseEntity.ok("Booking with id " + id + " was successfully deleted!");
        } catch (Exception e) {
            System.out.println("BAD REQUEST!");
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
            message.setRecipients(javax.mail.Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("Booking confirmation");
            message.setText("Your booking was added successfully!");
            message.setText("Your booking is was added for service: " + booking.getService().getName() + "\n\n" +
                    "Email:   " + booking.getEmail() + "\n" +
                    "Phone:   " + booking.getPhone() + "\n" +
                    "Date:    " + booking.getDate() + "\n" +
                    "Price:   " + booking.getService().getPrice() + "\n" +
                    "Company: " + booking.getService().getCompany().getName() + "\n" +
                    "THANK YOU, " + booking.getName() + " for choosing us!");
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean bookingNotExists(long id) {
        return (bookingRepository.findById(id) == null);
    }

    private boolean validMail(String email){
        Pattern p = Pattern.compile("\\b[A-Za-z0-9._%-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}\\b");
        Matcher m = p.matcher(email);

        return m.find();
    }

    private boolean validPhone(String phone){
        Pattern pattern = Pattern.compile("\\d{10}");
        Matcher m = pattern.matcher(phone);

        return m.find();
    }

    private boolean validDate(String date){
        if(date.length()>5 || date.length()<3) {
            return false;
        }
        return true;
    }

    public void BookingSanitization(Booking booking) {

        String bookingEntered[][] = new String[5][2];
        bookingEntered[0][0] = booking.getName();
        bookingEntered[1][0] = booking.getPhone();
        bookingEntered[2][0] = booking.getEmail();
        bookingEntered[3][0] = booking.getDate();

        bookingEntered[0][1] = "name";
        bookingEntered[1][1] = "phone";
        bookingEntered[2][1] = "email";
        bookingEntered[3][1] = "date";

        if(validMail(bookingEntered[2][0])==false){
            errorBooking+="Eroare "+bookingEntered[2][1]+ " ";
        }

        if(validDate(bookingEntered[3][0])==false){
            errorBooking+="Eroare "+bookingEntered[3][1]+ " " + bookingEntered[3][0];
        }

        if(validPhone(bookingEntered[1][0])==false){
            errorBooking+="Eroare "+bookingEntered[1][1]+ " ";
        }


    }
}
