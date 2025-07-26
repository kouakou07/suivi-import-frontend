package com.example.suivie_importBackend.models;

import java.util.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "motifs")
public class MotifM extends BaseM {

    private String code;
    private String libelle;

    public MotifM() {
    }

    public MotifM(String code, String libelle) {
        this.code = code;
        this.libelle = libelle;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public void update(String code, String libelle) {
        this.code = code;
        this.libelle = libelle;
        this.dateEdition = new Date();
    }

    @Override
    public String toString() {
        return "MotifM [code=" + code + ", libelle=" + libelle + "]";
    }

}
