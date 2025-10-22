package com.example.suivie_importBackend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaiementDto {

    @NotNull
    private LocalDate dateEcheance;

    @NotNull
    private String statut;

    @NotNull
    private LocalDate datePaiement;

    @NotNull
    private BigDecimal montant;

    @NotBlank
    private String devise;

    @NotBlank
    private String banque;

    @NotNull
    private Long commandeId;
}

