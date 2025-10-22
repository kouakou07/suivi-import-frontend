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
public class FdiDto {

    @NotBlank
    private String numeroFdi;

    @NotNull
    private LocalDate dateFdi;

    @NotBlank
    private String transitaire;

    @NotNull
    private Long commandeId;
}