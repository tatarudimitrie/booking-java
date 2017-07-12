package com.assist.bookingjava.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by cosmin on 12.07.2017.
 */
@Entity
@Table(name = "company")
public class Company implements Serializable {

    private static final long serialVersionUID = -3009157732242241606L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idCompany;

    @ManyToOne
    @JoinColumn(name="idAdmin", table="admins")
    private long idAdmins;

    @Column(name = "nameCompany")
    private String nameCompany;

    @Column(name = "descriptionCompany")
    private String descriptionCompany;

    @Column(name = "urlImage")
    private String urlImage;

    protected Company() {
    }


    public Company(long idAdmins, String nameCompany, String descriptionCompany, String urlImage) {
        this.idAdmins = idAdmins;
        this.nameCompany = nameCompany;
        this.descriptionCompany = descriptionCompany;
        this.urlImage = urlImage;
    }

    @Override
    public String toString() {
        return "Company{" +
                "idCompany=" + idCompany +
                ", admins=" + idAdmins +
                ", nameCompany='" + nameCompany + '\'' +
                ", descriptionCompany='" + descriptionCompany + '\'' +
                ", urlImage='" + urlImage + '\'' +
                '}';
    }
}
