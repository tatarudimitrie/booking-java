package com.assist.bookingjava.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "recoveries")
public class Recovery implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;
}
