package com.assist.bookingjava.services.interfaces;



        import org.springframework.mail.SimpleMailMessage;

public interface EmailInterface {
    public void sendEmail(SimpleMailMessage email);
}
