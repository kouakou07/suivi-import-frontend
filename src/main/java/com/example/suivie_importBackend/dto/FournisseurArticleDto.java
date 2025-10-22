package com.example.suivie_importBackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FournisseurArticleDto {

    private Long idArticle;
// private List<Long> fournisseurIds;
    private List<FournisseurArticleAssociationResponseDto> associations;
}
