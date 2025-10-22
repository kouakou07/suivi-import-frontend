package com.example.suivie_importBackend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommandeArticleDto {

    @NotNull
    private Long commandeId;

    @NotNull
    private Long articleId;

    @NotNull
    private Double quantite;

    @NotNull
    private Double prixUnitaire;

    @NotNull
    private Double quantiteRecue;
}
