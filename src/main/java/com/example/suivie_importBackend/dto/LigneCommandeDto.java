package com.example.suivie_importBackend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class LigneCommandeDto {

    @NotNull
    private Long articleId;

    @NotBlank
    private String designation;

    @NotBlank
    private String uniteGestion;

    @NotNull
    private Double quantite;

    @NotNull
    private Double prixUnitaire;

//    @NotBlank
    private String reference;

    @NotBlank
    private String referenceFournisseur;

//    @NotNull
//    private Long fournisseurId;

}
