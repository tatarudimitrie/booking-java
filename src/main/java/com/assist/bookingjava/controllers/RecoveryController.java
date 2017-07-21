package com.assist.bookingjava.controllers;

import com.assist.bookingjava.model.Admin;
import com.assist.bookingjava.model.ConfirmPass;
import com.assist.bookingjava.model.Recovery;
import com.assist.bookingjava.model.SendEmail;
import com.assist.bookingjava.repositories.AdminRepository;
import com.assist.bookingjava.services.RecoveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class RecoveryController {

    @Autowired
    private RecoveryService recoveryService;

    @Autowired
    private AdminRepository adminRepository;

    private static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    private final static String emailAddress = "assistbooking7@gmail.com";
    private final static String emailPassword = "Assist2017";
    private final static String recoveryURL = "http://192.168.150.225:8080/ResetPassword?token=";

    private boolean validMail(String email){
        Pattern p = Pattern.compile("\\b[A-Za-z0-9._%-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}\\b");
        Matcher m = p.matcher(email);

        return m.find();
    }

    @RequestMapping(value = "/forgot/password", method = RequestMethod.POST)
    public String processForgotPasswordForm(@RequestBody SendEmail email) {
        System.out.println("EMAIL RECOVERY for email '" + email.getEmail() + "'");

        if (!validMail(email.getEmail())) {
            return "Invalid email!";
        }

        Admin admin = adminRepository.findByEmail(email.getEmail());

        if (admin == null) {
            return "No admin with email '" + email.getEmail() + "' was found!";
        }

        Recovery recovery = new Recovery();
        recovery.setEmail(email.getEmail());
        recovery.setResetToken(UUID.randomUUID().toString());
        recoveryService.saveRecovery(recovery);

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
            message.setFrom(new InternetAddress(email.getEmail()));
            message.setRecipients(javax.mail.Message.RecipientType.TO, InternetAddress.parse(email.getEmail()));
            message.setSubject("Reset your password");
            message.setText("Reset password for email '" + recovery.getEmail() + "'\n" +
                    "Follow this link for password recovery: \n" + recoveryURL + recovery.getResetToken());
            Transport.send(message);
        } catch (MessagingException e) {
            System.out.println("Something went wrong!" + e.toString());
            throw new RuntimeException(e);
        }

        return "Recovery token was send successfully!";
    }

    @RequestMapping(value = "/reset", method = RequestMethod.POST)
    public String setNewPassword(@RequestBody ConfirmPass confirmPass) {
        Recovery recovery;
        recovery = recoveryService.findByResetToken(confirmPass.getToken());
        Admin admin = adminRepository.findByEmail(recovery.getEmail());

        if (admin == null) {
            return "No admin was found for that email address!";
        }

        admin.setPass(encryptPassword(confirmPass.getPassword()));
        recoveryService.deleteRecovery(recovery.getId());
        adminRepository.save(admin);

        return "Password reset successfully!";
    }

    private String encryptPassword(String inputPass) {
        return PASSWORD_ENCODER.encode(inputPass);
    }
}