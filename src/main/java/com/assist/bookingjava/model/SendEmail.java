package com.assist.bookingjava.model;

import java.io.Serializable;


public class SendEmail implements Serializable{
    String email;

    public SendEmail(String email) {
        this.email = email;
    }
    public SendEmail(){}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
