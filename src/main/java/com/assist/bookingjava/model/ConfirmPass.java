package com.assist.bookingjava.model;

import java.io.Serializable;

/**
 * Created by cosmin on 17.07.2017.
 */
public class ConfirmPass implements Serializable {
    String token;
    String password;
    String confirm;

    public ConfirmPass(String token, String password, String confirm) {
        this.token = token;
        this.password = password;
        this.confirm = confirm;
    }
    public ConfirmPass(){}
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    @Override
    public String toString() {
        return "ConfirmPass{" +
                "token='" + token + '\'' +
                ", password='" + password + '\'' +
                ", confirm='" + confirm + '\'' +
                '}';
    }
}
