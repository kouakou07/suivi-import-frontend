package com.example.suivie_importBackend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CommandeDto {

     private String numeroCommande;

     private String numeroDai;

    @NotBlank
    private String numeroCommandeFournisseur;

    @NotBlank
    private String numeroProforma;

    @NotNull
    private LocalDate dateProforma;

    @NotBlank
    private String devise;

    private String modeEnvoi;

    @NotBlank
    private String clientDestinataire;

    @NotNull
    private LocalDate dateLivraisonSouhaitee;

    @NotNull
    private LocalDate dateEtd;

    @NotNull
    private LocalDate dateEta;

    @NotNull
    private Double prixRevient;

    @NotNull
    private Double prixVente;

    @NotNull private Long incotermId;

    @NotNull private Long fournisseurId;

    @NotNull private Long utilisateurId;

//    @NotNull
    private List<CommandeArticleDto> articles;

//    @NotNull
    private List<DocumentCommandeDto> documents;

//    @NotNull
    private List<FdiDto> fdis;

//    @NotNull
    private List<BonLivraisonImportDto> bonsLivraison;

//    @NotNull
    private List<PaiementDto> paiements;

    // Liste des lignes de commande
    private List<LigneCommandeDto> lignes;
}
