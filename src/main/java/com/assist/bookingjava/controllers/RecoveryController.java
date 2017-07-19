package com.assist.bookingjava.controllers;


import com.assist.bookingjava.model.Admin;
import com.assist.bookingjava.model.ConfirmPass;
import com.assist.bookingjava.model.Recovery;
import com.assist.bookingjava.model.SendEmail;
import com.assist.bookingjava.repositories.AdminRepository;
import com.assist.bookingjava.services.RecoveryService;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final static String emailAddress = "assistbooking7@gmail.com";
    private final static String emailPassword = "Assist2017";
    private final static String recoveryURL = "10.0.2.15:8080/resetPassword?token=";
    public boolean validMail(String email){
        Pattern p = Pattern.compile("\\b[A-Za-z0-9._%-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}\\b");
        Matcher m = p.matcher(email);

        if (m.find()){
            return true;
        }else{
            return false;
        }

    }
    @RequestMapping(value = "/forgot/password", method = RequestMethod.POST)
    public String processForgotPasswordForm(@RequestBody SendEmail email)
    {
        if(validMail(email.getEmail())) {
            System.out.println("===>> " + email.getEmail());
            Admin admin = adminRepository.findByEmail(email.getEmail());

            if (admin != null) {
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
                    message.setFrom(new InternetAddress("bolohan46@gmail.com"));
                    message.setRecipients(javax.mail.Message.RecipientType.TO,
                            InternetAddress.parse("bolohan46@gmail.com"));
                    message.setSubject("Reset your password");
                    message.setText("To reset your password, for mail: " +
                            recovery.getEmail() + ". Click the link below:\n" + recoveryURL + recovery.getResetToken());
                    Transport.send(message);
                } catch (MessagingException e) {
                    throw new RuntimeException(e);
                }
                return "Success!";
            } else {
                return "Error! Email invalid";
            }
        }
        else {
            return "Invalid mail";
        }
    }

    @RequestMapping(value = "/reset", method = RequestMethod.POST)
    public String setNewPassword(@RequestBody ConfirmPass confirmPass) {

        Recovery recovery1;
        recovery1 = recoveryService.findByResetToken(confirmPass.getToken());
        Admin admin1 = adminRepository.findByEmail(recovery1.getEmail());
        if(admin1!=null) {
            admin1.setPass(confirmPass.getPassword());
            recoveryService.deleteRecovery(recovery1.getId());
            adminRepository.save(admin1);
            return "Success!";
        }
        else {
            return "Error!";
        }
    }
}