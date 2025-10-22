package com.example.suivie_importBackend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "acheteurs")
public class AcheteurM extends BaseM {

    private String civilite;

    private String prenom;

    private String nom;

    @Column(name = "raison_sociale")
    private String raisonSociale;

    @Column(name = "adresse_1")
    private String adresse1;

    @Column(name = "adresse_2")
    private String adresse2;

    private String ville;

    @Column(name = "code_postal")
    private String codePostal;

    @Column(name = "pays_code_iso", length = 3)
    private String paysCodeISO;

    private String telephone;

    private String email;

    private String numeroTVA;
}
