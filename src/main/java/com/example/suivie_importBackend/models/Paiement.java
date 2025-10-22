package com.example.suivie_importBackend.models;

import com.example.suivie_importBackend.Enum.StatutPaiement;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "paiements")
public class Paiement extends BaseM {

    private LocalDate dateEcheance;

    @Enumerated(EnumType.STRING)
    private StatutPaiement statut;

    private LocalDate datePaiement;

    private BigDecimal montant;

    private String devise;

    private String banque;

    @OneToOne
    @JoinColumn(name = "commande_id")
    private CommandeM commande;
}
