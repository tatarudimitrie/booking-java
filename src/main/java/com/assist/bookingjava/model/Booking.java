package com.assist.bookingjava.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "bookings")
public class Booking implements Serializable {

    private static final long serialVersionUID = -3009157732242241606L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Service service;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name ="phone")
    private String phone;

    @Column(name="date")
    private String date;

    protected Booking() {
    }

    public Booking(String name, String email, String phone, String date) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.date = date;
    }

    public Booking(long id_service, String name, String email, String phone, String date) {
        this.service = new Service(id_service, "", "", "", 0, 0d, "");
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", service=" + service +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}