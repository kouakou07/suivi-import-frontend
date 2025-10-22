package com.example.suivie_importBackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FournisseurArticleAssociationResponseDto {

    private Long id;
    private Long idFournisseur;
    private String codeFournisseur;
    private String intituleFournisseur;
}
