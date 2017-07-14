package com.assist.bookingjava.services;

import com.assist.bookingjava.services.interfaces.EmailInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;

/**
 * Created by cosmin on 14.07.2017.
 */
public class EmailServices implements EmailInterface {


    @Autowired
    private JavaMailSender mailSender;
    @Async
    public void sendEmail(SimpleMailMessage email) {
        mailSender.send(email);
    }
}
