package com.example.suivie_importBackend.service;

import com.example.suivie_importBackend.Enum.Deletion;
import com.example.suivie_importBackend.dto.FournisseurArticleAssociationResponseDto;
import com.example.suivie_importBackend.dto.FournisseurArticleDto;
import com.example.suivie_importBackend.models.FournisseurArticleM;
import com.example.suivie_importBackend.models.FournisseurM;
import com.example.suivie_importBackend.repository.ArticleRepository;
import com.example.suivie_importBackend.repository.FournisseurArticleRepository;
import com.example.suivie_importBackend.repository.FournisseurRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FournisseurArticleService {

    private FournisseurArticleRepository fournisseurArticleRepository;
    private ArticleRepository articleRepository;
    private FournisseurRepository fournisseurRepository;

    public FournisseurArticleService(FournisseurArticleRepository fournisseurArticleRepository, ArticleRepository articleRepository, FournisseurRepository fournisseurRepository) {
        this.fournisseurArticleRepository = fournisseurArticleRepository;
        this.articleRepository = articleRepository;
        this.fournisseurRepository = fournisseurRepository;
    }

    @Transactional
    public List<FournisseurArticleM> associerFournisseursArticle(Long articleId, List<Long> fournisseurIds) {
        fournisseurArticleRepository.findByIdAndDeleted(articleId, Deletion.NO);
        List<FournisseurArticleM> newAssociations = new ArrayList<>();
        if (fournisseurIds != null && !fournisseurIds.isEmpty()) {
            for (Long fournisseurId : fournisseurIds) {
                FournisseurArticleM association = FournisseurArticleM.builder()
                        .idArticle(articleId)
                        .idFournisseur(fournisseurId)
                        .build();
                newAssociations.add(association);
            }
            return fournisseurArticleRepository.saveAll(newAssociations);
        }
        return newAssociations;
    }

    public FournisseurArticleDto getFournisseursByArticle(Long articleId) {
        List<FournisseurArticleM> associations = fournisseurArticleRepository.findByIdArticleAndDeleted(articleId, Deletion.NO);
        List<FournisseurArticleAssociationResponseDto> associationResponseDtos = associations.stream()
                .map(associationResponseDto -> {
                             FournisseurM fournisseurM = fournisseurRepository
                            .findByIdAndDeleted(associationResponseDto.getIdFournisseur(), Deletion.NO)
                            .orElse(null);
                    return new FournisseurArticleAssociationResponseDto(
                            associationResponseDto.getId(),
                            associationResponseDto.getIdFournisseur(),
                            fournisseurM != null ? fournisseurM.getCodeFournisseur() : null,
                            fournisseurM != null ? fournisseurM.getIntituleFournisseur() : null
                    );
                })
                .collect(Collectors.toList());
        return new FournisseurArticleDto(articleId, associationResponseDtos);
    }

    public void supprimer(Long associationId) {
        FournisseurArticleM association = fournisseurArticleRepository
                .findByIdAndDeleted(associationId, Deletion.NO)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Association fournisseur-article non trouvée avec id " + associationId));
        association.setDeleted(Deletion.YES);
        fournisseurArticleRepository.save(association);
    }
}