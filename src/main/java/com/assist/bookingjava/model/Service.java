package com.assist.bookingjava.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by root on 12.07.2017.
 */
@Entity
@Table(name = "customer")
public class Service implements Serializable {

    private static final long serialVersionUID = -3009157732242241606L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idService;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idCompany")
    private Company company;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String desc;

    @Column(name = "duration")
    private String duration;

    @Column(name = "space")
    private Date space;

    @Column(name = "price")
    private double price;

    public Service(Company company, String firstName, String name, String desc, String duration, Date space, double price) {
        this.company = company;
        this.firstName = firstName;
        this.name = name;
        this.desc = desc;
        this.duration = duration;
        this.space = space;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Service{" +
                "idService=" + idService +
               // ", school=" + school +
                ", firstName='" + firstName + '\'' +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", duration='" + duration + '\'' +
                ", space=" + space +
                ", price=" + price +
                '}';
    }
}
