package com.example.suivie_importBackend.models;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "transitaires")
public class Transitaire extends BaseM {

    @Column(name = "code_transitaire")
    private String codeTransitaire;
    @Column(name = "intitule_transitaire")
    private String intituleTransitaire;
    private Date dateCreation;
    @Column(name = "nom_Contact")
    private String nomContact;
    private String telephone;
    private String telecopie;
    @Column(name = "e_mail")
    private String email;
    private String siret;
    @Column(name = "n_tva_intracommunautaire")
    private String nTvaIntracommunautaire;
    private String adresse;
    private String complement;
    @Column(name = "code_postal")
    private String codePostal;
    private String ville;
    private String region;
    private String pays;

    @ManyToOne
    @JoinColumn(name = "mode_paiement_id")
    private ModePaiement modePaiement;
    private String echeance;
    private String iban;
    private String bic;
    @Column(name = "contact_Email")
    private String contactEmail;
    @Column(name = "contact_Fonction")
    private String contactFonction;
    @Column(name = "contact_Prenoms")
    private String contactPrenoms;
    @Column(name = "contact_Nom")
    private String contactNom;

    @ManyToOne
    @JoinColumn(name = "devise_id")
    private Devise devise;
}
