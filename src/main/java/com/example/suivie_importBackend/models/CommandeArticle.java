package com.example.suivie_importBackend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "commande_articles")
public class CommandeArticle extends BaseM {

    @ManyToOne
    @JoinColumn(name = "commande_id")
    private CommandeM commande;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private ArticleM article;

    private Double quantite;

    private Double prixUnitaire;

    private Double quantiteRecue;
}
