package com.example.suivie_importBackend.vo;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BanqueVO {
    private String libelle;
    private String numeroCompte;
}
