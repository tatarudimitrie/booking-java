package com.assist.bookingjava.model;

<<<<<<< Updated upstream



=======
>>>>>>> Stashed changes
import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by cosmin on 12.07.2017.
 */
@Entity
@Table(name = "company")
<<<<<<< Updated upstream
=======
/*@SecondaryTables({
        @SecondaryTable(name="service", pkJoinColumns={
                @PrimaryKeyJoinColumn(name="idService") })
})*/
>>>>>>> Stashed changes
public class Company implements Serializable {

    private static final long serialVersionUID = -3009157732242241606L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idCompany;

<<<<<<< Updated upstream
     //@ManyToOne
     //@JoinColumn(name="idAdmin", table="admins")
     private long idAdmin;
=======
   // @ManyToOne
   //  @JoinColumn(name="idAdmin", table="admins")
   // private long idAdmins;
>>>>>>> Stashed changes

    @Column(name = "nameCompany")
    private String nameCompany;

    @Column(name = "descriptionCompany")
    private String descriptionCompany;

    @Column(name = "urlImage")
    private String urlImage;

    protected Company() {
    }


<<<<<<< Updated upstream
    public Company(long idAdmin, String nameCompany, String descriptionCompany, String urlImage) {
        this.idAdmin = idAdmin;
=======
    public Company(long idAdmins, String nameCompany, String descriptionCompany, String urlImage) {
       // this.idAdmins = idAdmins;
>>>>>>> Stashed changes
        this.nameCompany = nameCompany;
        this.descriptionCompany = descriptionCompany;
        this.urlImage = urlImage;
    }

    @Override
    public String toString() {
        return "Company{" +
                "idCompany=" + idCompany +
<<<<<<< Updated upstream
                ", admin=" + idAdmin +
=======
                ", admins=" +// idAdmins +
>>>>>>> Stashed changes
                ", nameCompany='" + nameCompany + '\'' +
                ", descriptionCompany='" + descriptionCompany + '\'' +
                ", urlImage='" + urlImage + '\'' +
                '}';
    }
<<<<<<< Updated upstream


    public long getIdCompany() {
        return idCompany;
    }

    public long getIdAdmins() {
        return idAdmin;
    }

    public String getNameCompany() {
        return nameCompany;
    }

    public String getDescriptionCompany() {
        return descriptionCompany;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setIdCompany(long idCompany) {
        this.idCompany = idCompany;
    }

    public void setIdAdmins(long idAdmins) {
        this.idAdmin = idAdmins;
    }

    public void setNameCompany(String nameCompany) {
        this.nameCompany = nameCompany;
    }

    public void setDescriptionCompany(String descriptionCompany) {
        this.descriptionCompany = descriptionCompany;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }



}
=======
}
>>>>>>> Stashed changes
