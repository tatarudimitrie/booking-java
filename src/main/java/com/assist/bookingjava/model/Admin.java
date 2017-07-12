/**
 * Created by myt on 12.07.2017.
 */

package com.assist.bookingjava.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "customer")
public class Admin implements Serializable {

    private static final long serialVersionUID = -3009157732242241606L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    protected Admin() {
    }

    public Admin(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format("Customer[id=%d, firstName='%s', lastName='%s']", id, firstName, lastName);
    }
}