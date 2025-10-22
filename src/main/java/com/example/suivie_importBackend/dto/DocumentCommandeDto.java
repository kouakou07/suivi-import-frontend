package com.example.suivie_importBackend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DocumentCommandeDto {

    @NotBlank
    private String typeDocument;  // FACTURE, BSC, ASSURANCE, etc.

    @NotBlank
    private String numero;

    @NotNull
    private LocalDate dateReception;

    @NotBlank
    private String lienFichier;

    @NotNull
    private Long commandeId;
}
