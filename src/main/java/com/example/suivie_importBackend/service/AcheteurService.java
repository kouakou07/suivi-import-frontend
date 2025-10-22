package com.example.suivie_importBackend.service;

import com.example.suivie_importBackend.Enum.Deletion;
import com.example.suivie_importBackend.Enum.Status;
import com.example.suivie_importBackend.dto.AcheteurDto;
import com.example.suivie_importBackend.models.AcheteurM;
import com.example.suivie_importBackend.repository.AcheteurRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AcheteurService {

    private final AcheteurRepository acheteurRepository;

    public AcheteurService(AcheteurRepository acheteurRepository) {
        this.acheteurRepository = acheteurRepository;
    }

    private AcheteurM toEntity(AcheteurDto dto) {
        AcheteurM acheteur = new AcheteurM();
        acheteur.setId(dto.getId());
        acheteur.setCivilite(dto.getCivilite());
        acheteur.setPrenom(dto.getPrenom());
        acheteur.setNom(dto.getNom());
        acheteur.setRaisonSociale(dto.getRaisonSociale());
        acheteur.setAdresse1(dto.getAdresse1());
        acheteur.setAdresse2(dto.getAdresse2());
        acheteur.setVille(dto.getVille());
        acheteur.setCodePostal(dto.getCodePostal());
        acheteur.setPaysCodeISO(dto.getPaysCodeISO());
        acheteur.setTelephone(dto.getTelephone());
        acheteur.setEmail(dto.getEmail());
        acheteur.setDeleted(Deletion.NO);
        acheteur.setNumeroTVA(dto.getNumeroTVA());
        return acheteur;
    }

    private AcheteurDto toDto(AcheteurM entity) {
        AcheteurDto dto = new AcheteurDto();
        dto.setId(entity.getId());
        dto.setCivilite(entity.getCivilite());
        dto.setPrenom(entity.getPrenom());
        dto.setNom(entity.getNom());
        dto.setRaisonSociale(entity.getRaisonSociale());
        dto.setAdresse1(entity.getAdresse1());
        dto.setAdresse2(entity.getAdresse2());
        dto.setVille(entity.getVille());
        dto.setCodePostal(entity.getCodePostal());
        dto.setPaysCodeISO(entity.getPaysCodeISO());
        dto.setTelephone(entity.getTelephone());
        dto.setEmail(entity.getEmail());
        dto.setNumeroTVA(entity.getNumeroTVA());
        return dto;
    }

    public AcheteurDto creerAcheteur(AcheteurDto dto) {
        if (dto.getId() != null) {
            Optional<AcheteurM> existing = acheteurRepository.findFirstByIdAndDeleted(dto.getId(), false);
            if (existing.isPresent()) {
                throw new RuntimeException("Cet acheteur existe déjà");
            }
        }
        AcheteurM entity = toEntity(dto);
        AcheteurM save = acheteurRepository.save(entity);
        return toDto(save);
    }

    public Optional<AcheteurDto> detailAcheteur(Long id) {
        return acheteurRepository.findById(id)
                .filter(a -> !a.getDeleted())
                .map(this::toDto);
    }

    public List<AcheteurDto> recupererTousLesAcheteurs() {
        return acheteurRepository.findAllByDeleted(Deletion.NO)
                .stream()
                .map(this::toDto)
                .toList();
    }

    public Page<AcheteurDto> recupererTousLesAcheteursAvecPagination(int page, int size) {
        Page<AcheteurM> pageResult = acheteurRepository.findAllByDeleted(false,PageRequest.of(page, 20));
        return pageResult.map(this::toDto);
    }

    public AcheteurDto modifierAcheteur(Long id, AcheteurDto dto) {
        return acheteurRepository.findById(id)
                .map(existing -> {
                    existing.setCivilite(dto.getCivilite());
                    existing.setPrenom(dto.getPrenom());
                    existing.setNom(dto.getNom());
                    existing.setRaisonSociale(dto.getRaisonSociale());
                    existing.setAdresse1(dto.getAdresse1());
                    existing.setAdresse2(dto.getAdresse2());
                    existing.setVille(dto.getVille());
                    existing.setCodePostal(dto.getCodePostal());
                    existing.setPaysCodeISO(dto.getPaysCodeISO());
                    existing.setTelephone(dto.getTelephone());
                    existing.setEmail(dto.getEmail());
                    existing.setStatus(Status.DEFAULT_ENABLE);
                    existing.setDeleted(Deletion.NO);
                    existing.setNumeroTVA(dto.getNumeroTVA());
                    existing.update();
                    acheteurRepository.save(existing);
                    return toDto(existing);
                })
                .orElseThrow(() -> new RuntimeException("Acheteur non trouvé"));
    }

    public void supprimerAcheteur(Long id) {
        acheteurRepository.findById(id)
                .ifPresent(a -> {
                    a.setDeleted(true);
                    a.update();
                    acheteurRepository.save(a);
                });
    }
}

