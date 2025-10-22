package com.example.suivie_importBackend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "article")
public class ArticleM extends BaseM{

    private String codeFamille;

    private String libelleFamille;

    private String referenceArticle;

    private String afRefFourniss;

    private String designationArticle;

    private String suiviStock;

    @ManyToOne
    @JoinColumn(name = "fournisseur_id")
    private FournisseurM fournisseurId;


    @ManyToOne
    @JoinColumn(name = "departement_id")
    private Departement departement;

    @ManyToOne
    @JoinColumn(name = "familleCentrale_id")
    private FamilleCentrale familleCentrale;

    @ManyToOne
    @JoinColumn(name = "uniteVente_id")
    private UniteVente uniteVente;

}
