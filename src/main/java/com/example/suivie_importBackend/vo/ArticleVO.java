package com.example.suivie_importBackend.vo;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ArticleVO {
    private Long id;
    private String referenceArticle;
    private String designationArticle;
    private String codeFamille;
    private String libelleFamille;
    private String afRefFourniss;
    private String codeFournisseur;
    private String intituleFournisseur;
    private String suiviStock;
    private String familleCentrale;
    private String libelleUniteVente;
    private String nomDepartement;
}
