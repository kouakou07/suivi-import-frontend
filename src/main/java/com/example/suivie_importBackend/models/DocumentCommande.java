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
@Table(name = "documents_commandes")
public class DocumentCommande extends BaseM {

    private String typeDocument;  // FACTURE, BSC, ASSURANCE, etc.
    private String numero;
    private LocalDate dateReception;
    private String lienFichier;

    @ManyToOne
    @JoinColumn(name = "commande_id")
    private CommandeM commande;
}
