package com.assist.bookingjava.model;

import org.hibernate.mapping.Set;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by cosmin on 14.07.2017.
 */
@Entity
@Table(name = "recoveries")
public class Recovery implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

   // @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "admin", orphanRemoval = true)
   // private Set<Company> companies;

    @Column(name = "email")
    private String email;

    @Column(name = "reset_token")
    private String resetToken;

    public Recovery(String email, String resetToken) {
        this.email = email;
        this.resetToken = resetToken;
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
