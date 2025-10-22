package com.example.suivie_importBackend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "bon_livraison_import")
public class BonLivraisonImport extends BaseM{
    private String numeroBe;
    private LocalDate dateEntree;
    private Boolean livraisonPartielle;

    @ManyToOne
    @JoinColumn(name = "commande_id")
    private CommandeM commande;
}
