package com.example.suivie_importBackend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "factures_transitaires")
public class FactureTransitaire extends BaseM{
    private String type;
    private String numero;
    private LocalDate date;
    private BigDecimal montant;
    private String devise;

    @ManyToOne
    @JoinColumn(name = "commande_id")
    private CommandeM commande;
}
