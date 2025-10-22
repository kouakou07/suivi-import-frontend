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
@Table(name = "lignes_commande")
public class LigneCommande extends BaseM{

    @ManyToOne
    @JoinColumn(name = "commande_id", nullable = false)
    private CommandeM commande;

    @ManyToOne
    @JoinColumn(name = "article_id", nullable = false)
    private ArticleM article;

    // 🔹 Données principales
    private String reference;
    private String designation;
    private String uniteGestion;
    private Double quantite;
    private Double prixAchat;
    private Double prixUnitaire;// Prix unitaire d’achat (devise)
    private Double totalAchat;              // Total achat en devise
    private Double maf;                     // Montant assurance & fret
    private Double fret;                    // Fret individuel
    private Double totalCfrDevise;          // Total CFR (en devise)
    private Double tauxChange;              // Taux de conversion devise -> CFA
    private Double totalCfrCfa;             // Total CFR en CFA
    private Double ddTaxeHt;                // Droits de douane & taxes HT
    private Double faTransitHt;             // Frais transit HT
    private Double totalTransit;            // Total frais transit
    private Double coutRevient;             // Coût de revient total
    private Double coeffCoutRevient;        // Coefficient CR/CFR
    private Double coutUnitaireHt;          // Coût unitaire HT
    private Double pvHtMarge40;             // Prix de vente HT marge 40%
    private Double pvHtMarge50;             // Prix de vente HT marge 50%
    private Double pvTtcMini;               // Prix TTC mini
    private Double pvTtcMaxi;               // Prix TTC maxi
    private Double coeffMultiplicateur;     // Coefficient multiplicateur

    // 🔹 Optionnels (calculs automatiques ou métadonnées)
    private String devise;                  // Devise utilisée (USD, EUR, etc.)
    private String transitaire;             // Nom du transitaire
    private String numeroBE;                // Numéro de bon d’entrée
    private String declarationDouane;
}
