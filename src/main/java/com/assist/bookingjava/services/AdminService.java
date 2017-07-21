package com.assist.bookingjava.services;

import com.assist.bookingjava.model.Booking;
import com.assist.bookingjava.services.interfaces.AdminInterface;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.assist.bookingjava.model.Admin;
import com.assist.bookingjava.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Service
public class AdminService implements AdminInterface {

    @Autowired
    private AdminRepository adminRepository;
    private String defaultPass = "******";
    private String nl = "\n";
    private final static String emailAddress = "assistbooking7@gmail.com";
    private final static String emailPassword = "Assist2017";

    private static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    public ResponseEntity findAllAdmins() {
        System.out.println(nl + "ADMIN GET: /admins/all");

        try {
            List<Admin> adminList = new ArrayList<>();

            for (Admin a : adminRepository.findAll()) {
                a.setPass(defaultPass);
                adminList.add(a);
            }

            System.out.println("OK: " + adminList.toString());
            return ResponseEntity.ok(adminList);
        } catch (Exception e) {
            System.out.println("BAD REQUEST!");
            return ResponseEntity.badRequest().body("Bad request! " + e.toString());
        }
    }

    public ResponseEntity findAdminById(long id) {
        System.out.println(nl + "ADMIN GET: /admins/id/{" + id + "}");

        try {
            Admin admin = adminRepository.findOne(id);
            admin.setPass(defaultPass);

            System.out.println("OK: " + admin.toString());
            return ResponseEntity.ok(admin);
        } catch (Exception e) {
            System.out.println("BAD REQUEST!");
            return ResponseEntity.badRequest().body("Bad request! " + e.toString());
        }
    }

    public ResponseEntity findAdminByName(String name){
        System.out.println(nl + "ADMIN GET: /admins/name/{" + name + "}");

        try {
            Admin admin = adminRepository.findByName(name);
            admin.setPass(defaultPass);

            System.out.println("OK: " + admin.toString());
            return ResponseEntity.ok(admin);
        } catch (Exception e) {
            System.out.println("BAD REQUEST!");
            return ResponseEntity.badRequest().body("Bad request! " + e.toString());
        }
    }

    public ResponseEntity<String> editAdmin(Admin admin) {
        System.out.println("ADMIN PUT: /admins/edit - for " + admin.toString());

        try {
            Admin tempAdmin = adminRepository.findByName(admin.getName());
            tempAdmin.setEmail(admin.getEmail());
            tempAdmin.setPass(encryptPassword(admin.getPass()));
            adminRepository.save(tempAdmin);

            System.out.println("OK: " + tempAdmin.toString());
            return ResponseEntity.ok("Admin edited successfully!");
        } catch (Exception e) {
            System.out.println("BAD REQUEST!");
            return  ResponseEntity.badRequest().body("Bad request! " + e.toString());
        }
    }

    public ResponseEntity<String> addAdmin(Admin admin) {
        System.out.println("ADMIN POST: /admins/add - for " + admin.toString());

        String sanitize = adminSanitize(admin);
        if (!sanitize.equals("")) {
            return ResponseEntity.badRequest().body("Wrong input!\n" + sanitize);
        }

        if (isDuplicateName(admin.getName())) {
            return ResponseEntity.badRequest().body("Duplicate name");
        }

        if (isDuplicateEmail(admin.getEmail())) {
            return ResponseEntity.badRequest().body("Duplicate email");
        }

        if (isInvalidPass(admin.getPass())) {
            return ResponseEntity.badRequest().body("Password length must be between 6..16");
        }

        try {
            admin.setPass(encryptPassword(admin.getPass()));
            adminRepository.save(admin);
            sendRegisterEmail(admin);

            System.out.println("OK: " + admin.toString());
            return ResponseEntity.ok("Admin registered successfully!");
        } catch (Exception e) {
            System.out.println("BAD REQUEST!");
            return ResponseEntity.badRequest().body("Something happened!");
        }
    }

    public ResponseEntity<String> deleteAdmin(long id) {
        System.out.println("ADMIN DELETE: /admins/delete - for admin with id " + id );

        try {
            adminRepository.delete(id);

            System.out.println("OK: Deleted!");
            return ResponseEntity.ok("Admin deleted successfully!");
        } catch (Exception e) {
            System.out.println("BAD REQUEST!");
            return ResponseEntity.badRequest().body("Bad request! " + e.toString());
        }
    }

    /* ADMIN INPUT CHECKS */
    private boolean isDuplicateName(String name) {
        return (adminRepository.findByName(name) != null);
    }

    private boolean isDuplicateEmail(String email) {
        return (adminRepository.findByEmail(email) != null);
    }

    private boolean isInvalidPass(String pass) {
        return !((pass.length() >= 6) && (pass.length() <= 16));
    }

    private String encryptPassword(String inputPass) {
        return PASSWORD_ENCODER.encode(inputPass);
    }

    private String adminSanitize(Admin admin) {
        String allowed = "@._=-ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
        String userEntered[][] = new String[3][2];
        userEntered[0][0] = admin.getName();
        userEntered[1][0] = admin.getPass();
        userEntered[2][0] = admin.getEmail();

        userEntered[0][1] = "Name";
        userEntered[1][1] = "Password";
        userEntered[2][1] = "Email";

        String errorString = "";

        for (int i = 0; i < userEntered.length-1; ++i) {
            for(int j=0;j<userEntered[j].length;j++)
            if (allowed.contains(userEntered[i][j])==false) {
        //        errorString += "Error  " + userEntered[i][1] + " ";
            }
        }

        try {
            InternetAddress emailAddr = new InternetAddress(userEntered[2][0]);
            emailAddr.validate();
        } catch (AddressException ex) {
            errorString += "Email"+ex.toString();
        }

        return errorString;
    }

    private void sendRegisterEmail(Admin admin) {
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
            String email = admin.getEmail();
            message.setFrom(new InternetAddress(email));
            message.setRecipients(javax.mail.Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("Account was created successfully!");
            message.setText("Admin register");
            message.setText("Admin registered successfully!");
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public String bulkAddAdmin() {
        adminRepository.save(new Admin("Tataru Dimitrie", "intern.dimitrie.tataru@assist.ro", encryptPassword("dimitrie")));
        adminRepository.save(new Admin("Leonte Andrei", "intern.andrei.leonte@assist.ro", encryptPassword("andrei")));
        adminRepository.save(new Admin("Nistor Florin", "intern.florin.nistor@assist.ro", encryptPassword("florin")));
        adminRepository.save(new Admin("Viziteu Andrei", "intern.andrei.viziteu@assist.ro", encryptPassword("andrei")));
        adminRepository.save(new Admin("Bolohan Cosmin", "intern.cosmin.bolohan@assist.ro", encryptPassword("cosmin")));
        adminRepository.save(new Admin("Bujdei Mihai", "intern.mihai.bujdei@assist.ro", encryptPassword("mihai")));
        return "Admin table was updated with six DEFAULT ROWS!";
    }
}