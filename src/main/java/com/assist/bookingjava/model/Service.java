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

    @ManyToOne
    @ForeignKey(name="FK_Company")
    private Company idCompany;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "duration")
    private String duration;

    @Column(name = "space")
    private Date space;

    @Column(name = "price")
    private double price;

    public Service(String name, String description, String duration, Date space, double price) {
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.space = space;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Service{" +
                "idService=" + idService +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", duration='" + duration + '\'' +
                ", space=" + space +
                ", price=" + price +
                '}';
    }
}
