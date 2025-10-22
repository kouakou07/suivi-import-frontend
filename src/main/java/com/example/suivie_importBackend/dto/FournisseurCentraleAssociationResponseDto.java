package com.example.suivie_importBackend.dto;

import lombok.Data;

@Data
public class FournisseurCentraleAssociationResponseDto {

    private Long id;
    private Long idFournisseur;
    private String codeFournisseur;
    private String intituleFournisseur;

    public FournisseurCentraleAssociationResponseDto(
            Long id,
            Long idFournisseur,
            String codeFournisseur,
            String intituleFournisseur
    ) {
        this.id = id;
        this.idFournisseur = idFournisseur;
        this.codeFournisseur = codeFournisseur;
        this.intituleFournisseur = intituleFournisseur;
    }
}
