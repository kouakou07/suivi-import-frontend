package com.example.suivie_importBackend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "fournisseurs_article")
public class FournisseurArticleM extends BaseM {

    @Column(name = "article_id")
    private Long idArticle;

    @Column(name = "fournisseur_id")
    private Long idFournisseur;

}
