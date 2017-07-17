package com.assist.bookingjava.services.interfaces;



        import org.springframework.mail.SimpleMailMessage;
/**
 * Created by cosmin on 14.07.2017.
 */
public interface EmailInterface {
    public void sendEmail(SimpleMailMessage email);
}
