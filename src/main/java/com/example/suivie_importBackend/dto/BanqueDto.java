package com.example.suivie_importBackend.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BanqueDto {

    private Long id;
    private String libelle;
    private String numeroCompte;
}
