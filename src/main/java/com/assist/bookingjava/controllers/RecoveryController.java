package com.assist.bookingjava.controllers;


import com.assist.bookingjava.model.Admin;
import com.assist.bookingjava.model.Recovery;
import com.assist.bookingjava.repositories.AdminRepository;
import com.assist.bookingjava.services.AdminService;
import com.assist.bookingjava.services.EmailServices;
import com.assist.bookingjava.services.RecoveryServices;
import com.sun.org.apache.regexp.internal.RE;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by cosmin on 14.07.2017.
 */
@RestController
public class RecoveryController {


    @Autowired
    private RecoveryServices recoveryServices;

    private EmailServices emailServices;

    @Autowired
    private AdminService adminService;
    private AdminRepository adminRepository;


    // Display forgotPassword page
   // @RequestMapping(value = "/forgot", method = RequestMethod.GET)
  //  public ModelAndView displayForgotPasswordPage() {
  //      return new ModelAndView("forgotPassword");
   // }

    // Process form submission from forgotPassword page
    @RequestMapping(value = "/forgot/password", method = RequestMethod.PUT)
    public String processForgotPasswordForm(@RequestBody String email) throws JSONException {

        // Lookup user in database by e-mail



           System.out.println("aici"  + email);
       List<Recovery> optional= recoveryServices.findByEmail(email);

      // List<Admin> adminSearchEmail= adminRepository.findByEmail(email);
       //  Optional<Recovery> optional = recoveryServices.findByEmail(email);
     //   List<Admin> optional1= (List<Admin>) adminService.findAdminByEmail(email.toString());
        //verifica daca e in admin
        //isPresent


        if (!optional.isEmpty()) {
            // adminRepository.save(admin);
            //return "PUT: Success!";
            // Generate random 36-character string token for reset password
            String emailsave = email.toString();
            Recovery recovery = new Recovery();
            recovery.setEmail(emailsave);
            recovery.setResetToken(UUID.randomUUID().toString());
            recoveryServices.saveRecovery(recovery);
            // Save token to database
            //recoveryServices.saveRecovery(recovery);

            //String appUrl = request.getScheme() + "://" + request.getServerName();
            //  String appUrl="test";

            // Email message
        /*    SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
            passwordResetEmail.setFrom("alexbolohan@stud.usv.ro");
            passwordResetEmail.setTo(recovery.getEmail());
            passwordResetEmail.setSubject("Password Reset Request");
            passwordResetEmail.setText("To reset your password, click the link below:\n" + appUrl
                    + "/reset?token=" + recovery.getResetToken());


            emailServices.sendEmail(passwordResetEmail);
*/
            // Add success message to view
            return "PUT: Success!";

        } else {
            return "Error! Email invalid";
        }
    }
        /*

        if (!optional.isPresent()) {
            modelAndView.addObject("errorMessage", "We didn't find an account for that e-mail address.");
        } else {

            // Generate random 36-character string token for reset password
            Recovery recovery = optional.get();
            recovery.setResetToken(UUID.randomUUID().toString());

            // Save token to database
            recoveryServices.saveRecovery(recovery);

            String appUrl = request.getScheme() + "://" + request.getServerName();

            // Email message
            SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
            passwordResetEmail.setFrom("mail_test@assist.com");
            passwordResetEmail.setTo(recovery.getEmail());
            passwordResetEmail.setSubject("Password Reset Request");
            passwordResetEmail.setText("To reset your password, click the link below:\n" + appUrl
                    + "/reset?token=" + recovery.getResetToken());

            emailServices.sendEmail(passwordResetEmail);

            // Add success message to view
            modelAndView.addObject("successMessage", "A password reset link has been sent to " + email);
        }

        modelAndView.setViewName("forgotPassword");
        return modelAndView;
        return "ok";
*/
        //  }
/*
    // Display form to reset password
    @RequestMapping(value = "/reset", method = RequestMethod.GET)
    public ModelAndView displayResetPasswordPage(ModelAndView modelAndView, @RequestParam("resetToken") String resetToken) {

        Optional<Recovery> recovery = recoveryServices.findByResetToken(resetToken);

        if (recovery.isPresent()) { // Token found in DB
            modelAndView.addObject("resetToken", resetToken);
        } else { // Token not found in DB
            modelAndView.addObject("errorMessage", "Oops!  This is an invalid password reset link.");
        }

        modelAndView.setViewName("resetPassword");
        return modelAndView;
    }

    // Process reset password form
    @RequestMapping(value = "/reset", method = RequestMethod.POST)
    public ModelAndView setNewPassword(ModelAndView modelAndView, @RequestParam Map<String, String> requestParams, RedirectAttributes redir) {

        // Find the user associated with the reset token
        Optional<Recovery> recovery = recoveryServices.findByResetToken(requestParams.get("resetToken"));

        // This should always be non-null but we check just in case
        if (recovery.isPresent()) {

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

    }