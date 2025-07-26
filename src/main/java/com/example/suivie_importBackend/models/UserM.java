package com.example.suivie_importBackend.models;

import java.util.Date;
import com.example.suivie_importBackend.Enum.Password;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCrypt;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Getter
@Setter
@Table(name = "users")
public class UserM extends BaseM {
    private String username;
    @Column(length = 500)
    @JsonIgnore
    private String password;
    @Column(length = 500)
    private String adresse;
    private String email;
    private String nom;
    private String prenom;

    private Integer essaie;

    private Boolean old;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateConnection;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateActivity;

    @ManyToOne(cascade = CascadeType.REMOVE)
    private RoleM role;

    public UserM() {
    }

    public UserM(String username, String adresse, String email, String nom, String prenom,
            RoleM role) {
        this.username = username.toUpperCase();
        this.adresse = adresse;
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.role = role;
        this.essaie = 0;
        this.old = Password.UNCHANGED;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        try {
            String saltPassword = BCrypt.gensalt(12);
            this.password = BCrypt.hashpw(password, saltPassword);
        } catch (Exception e) {

        }

    }

    @JsonIgnore
    public void update(String nom, String prenoms, String email, RoleM role) {
        this.nom = nom;
        this.prenom = prenoms;
        this.email = email;
        this.role = role;
        super.update();
    }

    @JsonIgnore
    public void update(String nom, String prenoms, String email) {
        this.nom = nom;
        this.prenom = prenoms;
        this.email = email;
        super.update();
    }

    public Integer getEssaie() {
        return essaie;
    }

    public void setEssaie(Integer essaie) {
        this.essaie = essaie;
    }

    public Date getDateConnection() {
        return dateConnection;
    }

    public void setDateConnection(Date dateConnection) {
        this.dateConnection = dateConnection;
    }

    public void updateConnection(Integer essaie) {
        this.essaie = essaie;
    }

    public Date getDateActivity() {
        return dateActivity;
    }

    public void setDateActivity(Date dateActivity) {
        this.dateActivity = dateActivity;
    }

    public Boolean getOld() {
        return old;
    }

    public void setOld(Boolean old) {
        this.old = old;
    }

    @Override
    public String toString() {
        return "UserM [username=" + username + ", email=" + email + ", nom=" + nom
                + ", prenom=" + prenom + ", role=" + role.getLibelle() + "]";
    }

}
