package com.example.suivie_importBackend.models;

import com.example.suivie_importBackend.Enum.TypeFournisseur;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "fournisseurs")
public class FournisseurM extends BaseM {

    @Column(name = "code_fournisseur")
    private String codeFournisseur;
    @Column(name = "intitule_fournisseur")
    private String intituleFournisseur;
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
    @Column(name = "statistiques_1")
    private String statistiques1;
    @Column(name = "statistiques_2")
    private String statistiques2;
    @Column(name = "statistiques_3")
    private String statistiques3;
    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private TypeFournisseur type = TypeFournisseur.SIMPLE;
    @ManyToOne
    @JoinColumn(name = "mode_paiement_id")
    private ModePaiement modePaiement;

    @ManyToOne
    @JoinColumn(name = "devise_id")
    private Devise devise;

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

}
