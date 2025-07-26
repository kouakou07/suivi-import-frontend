package com.example.suivie_importBackend.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "audits")
public class AuditM extends BaseM {
    @Column(length = 2500)
    private String libelle;
    @Column(length = 5000)
    private String objet;

    @ManyToOne(cascade = CascadeType.REMOVE)
    private UserM user;

    public AuditM() {
    }

    public AuditM(String libelle, String object, Integer status, UserM user) {
        this.libelle = libelle;
        this.objet = object;
        this.user = user;
        this.status = status;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getObjet() {
        return objet;
    }

    public void setObjet(String object) {
        this.objet = object;
    }

    public UserM getUser() {
        return user;
    }

    public void setUser(UserM user) {
        this.user = user;
    }

}
