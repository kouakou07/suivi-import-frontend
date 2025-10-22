package com.example.suivie_importBackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class FournisseurCentraleDto {
    private Long idFournisseurCentrale;
//  private List<FournisseurM> fournisseurs;
    private List<FournisseurCentraleAssociationResponseDto> associations;

}
