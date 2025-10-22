package com.example.suivie_importBackend.dto;

import com.example.suivie_importBackend.models.FournisseurM;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class FournisseurDto {
    private Long id;
    private String codeFournisseur;
    private String intituleFournisseur;
    private String nomContact;
    private String telephone;
    private String telecopie;
    private String email;
    private String siret;
    private String nTvaIntracommunautaire;
    private String adresse;
    private String complement;
    private String codePostal;
    private String ville;
    private String region;
    private String pays;
    private String statistiques1;
    private String statistiques2;
    private String statistiques3;
    private String type;
    private Long modePaiementId;
    private String echeance;
    private String iban;
    private String bic;
    private String contactEmail;
    private String contactFonction;
    private String contactPrenoms;
    private String contactNom;
    private Long deviseId;

}
