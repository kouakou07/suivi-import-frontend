package com.example.suivie_importBackend.models;

import com.example.suivie_importBackend.Enum.StatusCommande;
import com.example.suivie_importBackend.Enum.TypeFournisseur;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "commandes")
public class CommandeM extends BaseM {

    @Column(name = "numero_commande", nullable = false, unique = true)
    private String numeroCommande;

    private  String numeroDai;

    private String numeroProforma;

    private LocalDate dateProforma;

    @ManyToOne(fetch = FetchType.LAZY)
    private Incoterm incoterm;

    private String devise;

    private String modeEnvoi;

    private String clientDestinataire;

//    private String statut;

    @Enumerated(EnumType.STRING)
    private StatusCommande statut;

    private LocalDate dateLivraisonSouhaitee;

    private LocalDate dateEtd;

    private LocalDate dateEta;

    private Double prixRevient;

    private Double prixVente;

    @Enumerated(EnumType.STRING)
    private TypeFournisseur typeFournisseur;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fournisseur_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private FournisseurM fournisseur;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserM utilisateur;

    @OneToMany(mappedBy = "commande", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<LigneCommande> lignes = new ArrayList<>();

    // Documents associés (factures, BSC, assurance, etc.)
    @OneToMany(mappedBy = "commande", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DocumentCommande> documents = new ArrayList<>();

    @OneToMany(mappedBy = "commande", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Fdi> fdis;

    // Bons de livraison (livraisons partielles)
    @OneToMany(mappedBy = "commande", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BonLivraisonImport> bonsLivraison = new ArrayList<>();

    // Paiements liés
    @OneToMany(mappedBy = "commande", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Paiement> paiements = new ArrayList<>();
}
