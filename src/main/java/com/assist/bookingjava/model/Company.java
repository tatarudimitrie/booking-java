package com.assist.bookingjava.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "companies", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
public class Company implements Serializable {

    private static final long serialVersionUID = -3009157732242241606L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Admin admin;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "image_url")
    private String image_url;

    public Company() {}

    public Company(long idAdmin, String name, String description, String image_url) {
        this.admin = new Admin(idAdmin,"", "","");
        this.name = name;
        this.description = description;
        this.image_url = image_url;
    }

    public Company(String adminEmail, String name, String description, String image_url) {
        this.admin = new Admin(0,"",adminEmail,"");
        this.name = name;
        this.description = description;
        this.image_url = image_url;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", admin=" + admin + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", image_url='" + image_url + '\'' +
                '}';
    }
}
