package com.example.suivie_importBackend.dto;


import lombok.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class FournisseurExcelDto {
    private String codeFournisseur;
    private String intituleFournisseur;
    private Date dateCreation;
    private String nomContact;
    private String telephone;
    private String telecopie;
    private String eMail;
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
    private Date dateEdition;
    private Boolean deleted;
    private Integer status;


}
