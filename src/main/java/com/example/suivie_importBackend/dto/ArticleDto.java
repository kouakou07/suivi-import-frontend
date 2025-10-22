package com.example.suivie_importBackend.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ArticleDto {
    private Long id;
    private String referenceArticle;
    private String designationArticle;
    private String codeFamille;
    private String libelleFamille;
    private String afRefFourniss;
    private String codeFournisseur;
    private String intituleFournisseur;
    private String suiviStock;
    private Long familleCentraleId;   // <-- FK en id
    private Long departementId;      // <-- FK en id
    private Long uniteVenteId;
}
