package com.assist.bookingjava.services;

import com.assist.bookingjava.model.Admin;
import com.assist.bookingjava.model.Recovery;
import com.assist.bookingjava.repositories.AdminRepository;
import com.assist.bookingjava.repositories.RecoveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

@Service("recoveryServices")
public class RecoveryService {
    @Autowired
    private RecoveryRepository recoveryRepository;
    @Autowired
    private RecoveryService recoveryService;
    @Autowired
    private AdminRepository adminRepository;

    private final static String emailAddress = "assistbooking7@gmail.com";
    private final static String emailPassword = "Assist2017";
    private final static String recoveryURL = "localhost:8080/reset?token=";

    public String sendEmail(String email) {
        /*Admin admin = adminRepository.findByEmail(email);
        if(admin!=null)
        {
            Recovery recovery = new Recovery();
            recovery.setEmail(email);
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
                message.setSubject("Reset password");
                message.setText("To reset your password," +
                        recovery.getEmail() + " click the link below:\n" + recoveryURL + recovery.getResetToken());

                Transport.send(message);
                System.out.println("Email was sent, to: " + email);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
            return "Success!";
        } else {
            return "Error! Email invalid";
        }*/
        return "";
    }




    public List<Recovery> findByEmail(String email) {
        return recoveryRepository.findByEmail(email);
    }
    public Recovery findByResetToken(String resetToken) {
        return recoveryRepository.findByResetToken(resetToken);
    }
    public void saveRecovery(Recovery recovery) {
        recoveryRepository.save(recovery);
    }

}
