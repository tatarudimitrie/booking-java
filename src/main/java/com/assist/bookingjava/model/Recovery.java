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

    @Column(name = "name")
    private String name;
}
