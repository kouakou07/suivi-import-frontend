package com.example.suivie_importBackend.service;

import com.example.suivie_importBackend.Enum.Deletion;
import com.example.suivie_importBackend.Enum.TypeFournisseur;
import com.example.suivie_importBackend.dto.FournisseurCentraleAssociationResponseDto;
import com.example.suivie_importBackend.dto.FournisseurCentraleDto;
import com.example.suivie_importBackend.models.FournisseurCentraleM;
import com.example.suivie_importBackend.models.FournisseurM;
import com.example.suivie_importBackend.repository.FournisseurCentraleRepository;
import com.example.suivie_importBackend.repository.FournisseurRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FournisseurCentraleService {

    private final FournisseurCentraleRepository fournisseurCentraleRepository;
    private final FournisseurRepository fournisseurRepository;

    public FournisseurCentraleService(FournisseurCentraleRepository fournisseurCentraleRepository,
                                      FournisseurRepository fournisseurRepository) {
        this.fournisseurCentraleRepository = fournisseurCentraleRepository;
        this.fournisseurRepository = fournisseurRepository;
    }

    /**
     * Associer une liste de fournisseurs simples à un fournisseur central.
     */
    @Transactional
    public List<FournisseurCentraleM> associerFournisseursACentrale(Long idFournisseurCentrale, List<Long> fournisseursIds) {
        FournisseurM centrale = fournisseurRepository.findByIdAndDeleted(idFournisseurCentrale, Deletion.NO)
                .orElseThrow(() -> new RuntimeException("Centrale introuvable"));
        if (centrale.getType() != TypeFournisseur.CENTRALE) {
            throw new RuntimeException("Le fournisseur spécifié n’est pas une centrale !");
        }
        List<FournisseurCentraleM> newAssociations = new ArrayList<>();
        if (fournisseursIds != null && !fournisseursIds.isEmpty()) {
            for (Long fournisseurId : fournisseursIds) {
                FournisseurM fournisseur = fournisseurRepository.findByIdAndDeleted(fournisseurId, Deletion.NO)
                        .orElseThrow(() -> new RuntimeException("Fournisseur simple introuvable"));
                if (fournisseur.getType() ==  TypeFournisseur.CENTRALE) {
                    throw new RuntimeException("Impossible d’associer un fournisseur central à un autre central !");
                }
                FournisseurCentraleM association = FournisseurCentraleM.builder()
                        .idFournisseurCentrale(idFournisseurCentrale)
                        .idFournisseur(fournisseurId)
                        .build();
                newAssociations.add(association);
            }
            return fournisseurCentraleRepository.saveAll(newAssociations);
        }
        return newAssociations;
    }

    public FournisseurCentraleDto getFournisseursByCentrale(Long idFournisseurCentrale) {
        List<FournisseurCentraleM> associations =
                fournisseurCentraleRepository.findByIdFournisseurCentraleAndDeleted(idFournisseurCentrale, Deletion.NO);
        System.out.println("Associations trouvées: " + associations.size());
        List<FournisseurCentraleAssociationResponseDto> associationDtos = associations.stream()
                .map(assoc -> {
                    FournisseurM fournisseur = fournisseurRepository
                            .findByIdAndDeleted(assoc.getIdFournisseur(), Deletion.NO)
                            .orElse(null);
                    return new FournisseurCentraleAssociationResponseDto(
                            assoc.getId(),
                            assoc.getIdFournisseur(),
                            fournisseur != null ? fournisseur.getCodeFournisseur() : null,
                            fournisseur != null ? fournisseur.getIntituleFournisseur() : null
                    );
                })
                .collect(Collectors.toList());
        return new FournisseurCentraleDto(idFournisseurCentrale, associationDtos);
    }

    public void supprimerAssociation(Long associationId) {
        FournisseurCentraleM association = fournisseurCentraleRepository
                .findByIdAndDeleted(associationId, Deletion.NO)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Association fournisseur-centrale non trouvée avec id " + associationId));
        association.setDeleted(Deletion.YES);
        fournisseurCentraleRepository.save(association);
    }

    public void supprimerFournisseurAssocie( Long idFournisseur) {
        FournisseurCentraleM association = fournisseurCentraleRepository
                .findByIdFournisseurAndDeleted(idFournisseur, Deletion.NO)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Association fournisseur-centrale non trouvée avec centrale id " + idFournisseur));
        association.setDeleted(Deletion.YES);
        fournisseurCentraleRepository.save(association);
    }
}
