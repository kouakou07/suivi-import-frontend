package com.example.suivie_importBackend.dto;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CommandeListDto {

    private Long id;
    private String numeroCommande;
    private String numeroDai;
    private String clientDestinataire;
    private LocalDate dateProforma;
    private String modeEnvoi;
    private String statut;
    private String incotermSignification;
    private String devise;
}
