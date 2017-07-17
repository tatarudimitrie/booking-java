package com.assist.bookingjava.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "recoveries")
public class Recovery implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "email")
    private String email;

    @Column(name = "reset_token")
    private String resetToken;

    public Recovery(String email, String resetToken) {
        this.email = email;
        this.resetToken = resetToken;
    }
    public Recovery() {

    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getResetToken() {
        return resetToken;
    }

    public void setResetToken(String resetToken) {
        this.resetToken = resetToken;
    }

    @Override
    public String toString() {
        return "Recovery{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", resetToken='" + resetToken + '\'' +
                '}';
    }
}
