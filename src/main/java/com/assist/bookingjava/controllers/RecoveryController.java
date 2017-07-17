package com.assist.bookingjava.controllers;


import com.assist.bookingjava.model.Admin;
import com.assist.bookingjava.model.ConfirmPass;
import com.assist.bookingjava.model.Recovery;
import com.assist.bookingjava.repositories.AdminRepository;
import com.assist.bookingjava.services.AdminService;
import com.assist.bookingjava.services.RecoveryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.*;

/**
 * Created by cosmin on 14.07.2017.
 */
@RestController
public class RecoveryController {

    @Autowired
    private RecoveryServices recoveryServices;
    @Autowired
    private AdminService adminService;
    @Autowired
    private AdminRepository adminRepository;

    private final static String emailAddress = "assistbooking7@gmail.com";
    private final static String emailPassword = "Assist2017";
    private final static String recoveryURL = "localhost:8080/reset?token=";

    @RequestMapping(value = "/forgot/password", method = RequestMethod.PUT)
    public String processForgotPasswordForm(@RequestBody String email)
    {
        Admin admin = adminRepository.findByEmail(email);

        if(admin!=null)
        {
            Recovery recovery = new Recovery();
            recovery.setEmail(email);
            recovery.setResetToken(UUID.randomUUID().toString());
            recoveryServices.saveRecovery(recovery);

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
        }
    }

    // Process reset password form
    @RequestMapping(value = "/reset", method = RequestMethod.POST)
    public void setNewPassword(@RequestBody ConfirmPass confirmPass) {

        System.out.println("===>> "+confirmPass.toString());
        Recovery recovery1= recoveryServices.findByResetToken(confirmPass.getToken());
        System.out.println("===>> "+recovery1.toString());
        // This should always be non-null but we check just in case
       /* if (recovery.isPresent()) {

            Recovery resetPasswordAdmin = recovery.get();


            // Set new password
            //      resetUser.setPassword(bCryptPasswordEncoder.encode(requestParams.get("password")));

            // Set the reset token to null so it cannot be used again
            //   resetUser.setResetToken(null);

            // Save user
            //  userService.saveUser(resetUser);

            // In order to set a model attribute on a redirect, we must use
            // RedirectAttributes
            redir.addFlashAttribute("successMessage", "You have successfully reset your password.  You may now login.");

            modelAndView.setViewName("redirect:login");
            return modelAndView;

        } else {
            modelAndView.addObject("errorMessage", "Oops!  This is an invalid password reset link.");
            modelAndView.setViewName("resetPassword");
        }

        return modelAndView;
    }

    // Going to reset page without a token redirects to login page
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ModelAndView handleMissingParams(MissingServletRequestParameterException ex) {
        return new ModelAndView("redirect:login");
    }*/

    }}