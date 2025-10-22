package com.example.suivie_importBackend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
@Table(name = "calculs_prix")
public class CalculPrix extends BaseM{

    private BigDecimal prixRevient;
    private BigDecimal prixVente;
    private LocalDate dateCalcul;

    @OneToOne
    @JoinColumn(name = "commande_id")
    private CommandeM commande;
}
