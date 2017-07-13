package com.assist.bookingjava.model;

import javax.persistence.*;
import java.io.Serializable;

/* Add comment test */
@Entity
@Table(name = "admins")
public class Admin implements Serializable {

    private static final long serialVersionUID = -3009157732242241606L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idAdmin;

    @Column(name = "name")
    private String name;

    @Column(name = "mail")
    private String mail;

    @Column(name = "pass")
    private String pass;

    public Admin() {}

    public Admin(String name, String mail, String pass) {
        this.name = name;
        this.mail = mail;
        this.pass = pass;
    }

    public long getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(long idAdmin) {
        this.idAdmin = idAdmin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPass() {
        return pass;
    }




    @Override
    public String toString() {
        return "Admin{" +
                "idAdmin=" + idAdmin +
                ", name='" + name + "'" +
                ", mail='" + mail + "'" +
                ", pass='" + pass + "'" +
                '}';
    }
}