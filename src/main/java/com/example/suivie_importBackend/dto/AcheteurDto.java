package com.example.suivie_importBackend.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AcheteurDto {
    private Long id;
    private String civilite;
    private String prenom;
    private String nom;
    private String raisonSociale;
    private String adresse1;
    private String adresse2;
    private String ville;
    private String codePostal;
    private String paysCodeISO;
    private String telephone;
    private String email;
    private String numeroTVA;
}
