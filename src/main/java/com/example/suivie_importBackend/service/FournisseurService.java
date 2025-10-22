package com.example.suivie_importBackend.service;

import com.example.suivie_importBackend.Enum.Deletion;
import com.example.suivie_importBackend.Enum.TypeFournisseur;
import com.example.suivie_importBackend.dto.FournisseurDto;
import com.example.suivie_importBackend.models.Devise;
import com.example.suivie_importBackend.models.FournisseurM;
import com.example.suivie_importBackend.models.ModePaiement;
import com.example.suivie_importBackend.repository.DeviseRepository;
import com.example.suivie_importBackend.repository.FournisseurRepository;
import com.example.suivie_importBackend.repository.ModePaiementRepository;
import jakarta.transaction.Transactional;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class FournisseurService {

    private final FournisseurRepository fournisseurRepository;
    private final UserService userService;
    private final ModePaiementRepository modePaiementRepository;
    private final DeviseRepository deviseRepository;

    public FournisseurService(FournisseurRepository fournisseurRepository, UserService userService, ModePaiementRepository modePaiementRepository,DeviseRepository deviseRepository) {
        this.fournisseurRepository = fournisseurRepository;
        this.userService = userService;
        this.modePaiementRepository = modePaiementRepository;
        this.deviseRepository = deviseRepository;
    }

    public List<FournisseurDto> recupererTousLesFournisseurs() {
        return fournisseurRepository.findAllByDeleted(Deletion.NO)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public Page<FournisseurDto> recupererTousLesFournisseursAvecPagination(int page) {
        Page<FournisseurM> fournisseursPage = fournisseurRepository.findAllByDeletedOrderByCodeFournisseurNum(Deletion.NO, PageRequest.of(page, 20));
        return fournisseursPage.map(this::mapToDto);
    }

    public String supprimerFournisseur(Long id) {
        Optional<FournisseurM> optionalFournisseur = fournisseurRepository.findFirstByIdAndDeleted(id, Deletion.NO);
        if (optionalFournisseur.isPresent()) {
            FournisseurM recupererFournisseur = optionalFournisseur.get();
            recupererFournisseur.setDeleted(Deletion.YES);
            fournisseurRepository.save(recupererFournisseur);
            return "Fournisseur supprimé avec succès";
        } else {
            throw new RuntimeException("Fournisseur non trouvé ou déjà supprimé");
        }
    }

    @Transactional
    public FournisseurDto creerFournisseur(FournisseurDto fournisseurDto) {
        FournisseurM fournisseur = new FournisseurM();
        mapToEntity(fournisseurDto, fournisseur);

        fournisseur.setDeleted(Deletion.NO);
        fournisseur.setType(fournisseurDto.getType() != null ? TypeFournisseur.valueOf(fournisseurDto.getType()) : TypeFournisseur.SIMPLE);

        if (fournisseurDto.getModePaiementId() != null) {
            ModePaiement modePaiement = modePaiementRepository.findFirstByIdAndDeleted(fournisseurDto.getModePaiementId(), Deletion.NO)
                    .orElseThrow(() -> new NoSuchElementException("mode non trouvé"));
            fournisseur.setModePaiement(modePaiement);
        } else {
            fournisseur.setModePaiement(null);
        }

        if (fournisseurDto.getDeviseId() != null) {
            Devise devise = deviseRepository.findFirstByIdAndDeleted(fournisseurDto.getDeviseId(), Deletion.NO)
                    .orElseThrow(() -> new NoSuchElementException("la devise non trouvée"));
            fournisseur.setDevise(devise);
        } else {
            fournisseur.setDevise(null);
        }

        FournisseurM fournisseurCree = fournisseurRepository.save(fournisseur);
        return mapToDto(fournisseurCree);
    }

    public FournisseurDto mettreAJourFournisseur(FournisseurDto fournisseurDto) {
        Optional<FournisseurM> optionalFournisseur = fournisseurRepository.findFirstByIdAndDeleted(fournisseurDto.getId(), Deletion.NO);
        if (optionalFournisseur.isPresent()) {
            FournisseurM fournisseur = optionalFournisseur.get();
            mapToEntity(fournisseurDto, fournisseur);
            FournisseurM fournisseurMisAJour = fournisseurRepository.save(fournisseur);
            return mapToDto(fournisseurMisAJour);
        }
        throw new NoSuchElementException("Fournisseur non trouvé");
    }

    public FournisseurDto detailFournisseur(Long fournisseurId) {
        return fournisseurRepository.findFirstByIdAndDeleted(fournisseurId, Deletion.NO)
                .map(this::mapToDto)
                .orElseThrow(() -> new NoSuchElementException("Fournisseur non trouvé"));
    }

    public FournisseurDto recupererFournisseurParCodeOuIntitule(String valeurRecherchee) {
        FournisseurM fournisseurM = fournisseurRepository.findByCodeOrIntitule(valeurRecherchee, Deletion.NO);
        if (fournisseurM != null) {
            return mapToDto(fournisseurM);
        }
        return null;
    }

    public Page<FournisseurDto> rechercherFournisseursParCode(String codeFournisseur, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("codeFournisseur").ascending());
        if (codeFournisseur == null || codeFournisseur.isEmpty()) {
            return fournisseurRepository.findAllByDeleted(Deletion.NO, pageRequest)
                    .map(this::mapToDto);
        }
        return fournisseurRepository.findByCodeFournisseurContainingIgnoreCaseAndDeleted(
                codeFournisseur, Deletion.NO, pageRequest
        ).map(this::mapToDto);
    }

    @Transactional
    public void importerDepuisExcel(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("Veuillez sélectionner un fichier Excel.");
        }
        try (InputStream is = file.getInputStream();
             Workbook workbook = new XSSFWorkbook(is)) {
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();
            boolean firstRow = true;
            while (rows.hasNext()) {
                Row row = rows.next();
                if (firstRow) {
                    firstRow = false;
                    continue;
                }
                FournisseurM fournisseur = new FournisseurM();
                fournisseur.setCodeFournisseur(getCellValue(row.getCell(0)));
                fournisseur.setIntituleFournisseur(getCellValue(row.getCell(1)));
                if (row.getCell(2) != null && DateUtil.isCellDateFormatted(row.getCell(2))) {
                    fournisseur.setDateCreation(row.getCell(2).getDateCellValue());
                } else {
                    fournisseur.setDateCreation(new Date());
                }
                fournisseur.setNomContact(getCellValue(row.getCell(2)));
                fournisseur.setTelephone(getCellValue(row.getCell(3)));
                fournisseur.setTelecopie(getCellValue(row.getCell(4)));
                fournisseur.setEmail(getCellValue(row.getCell(5)));
                fournisseur.setSiret(getCellValue(row.getCell(6)));
                fournisseur.setNTvaIntracommunautaire(getCellValue(row.getCell(7)));
                fournisseur.setAdresse(getCellValue(row.getCell(8)));
                fournisseur.setComplement(getCellValue(row.getCell(9)));
                fournisseur.setCodePostal(getCellValue(row.getCell(10)));
                fournisseur.setVille(getCellValue(row.getCell(11)));
                fournisseur.setRegion(getCellValue(row.getCell(12)));
                fournisseur.setPays(getCellValue(row.getCell(13)));
                fournisseur.setStatistiques1(getCellValue(row.getCell(14)));
                fournisseur.setStatistiques2(getCellValue(row.getCell(15)));
                fournisseur.setStatistiques3(getCellValue(row.getCell(16)));
                if (row.getCell(18) != null && DateUtil.isCellDateFormatted(row.getCell(18))) {
                    fournisseur.setDateEdition(row.getCell(18).getDateCellValue());
                } else {
                    fournisseur.setDateEdition(new Date());
                }
                fournisseur.setDeleted(false);
                fournisseur.setStatus(1);

                fournisseurRepository.save(fournisseur);
            }
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de l'import Excel: " + e.getMessage(), e);
        }
    }

    private String getCellValue(Cell cell) {
        if (cell == null) return null;
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue().trim();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getLocalDateTimeCellValue().toString();
                }
                return String.valueOf((long) cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            default:
                return null;
        }
    }

    private FournisseurDto mapToDto(FournisseurM fournisseur) {
        FournisseurDto dto = new FournisseurDto();
        dto.setId(fournisseur.getId());
        dto.setCodeFournisseur(fournisseur.getCodeFournisseur());
        dto.setIntituleFournisseur(fournisseur.getIntituleFournisseur());
        dto.setNomContact(fournisseur.getNomContact());
        dto.setTelephone(fournisseur.getTelephone());
        dto.setTelecopie(fournisseur.getTelecopie());
        dto.setEmail(fournisseur.getEmail());
        dto.setSiret(fournisseur.getSiret());
        dto.setNTvaIntracommunautaire(fournisseur.getNTvaIntracommunautaire());
        dto.setAdresse(fournisseur.getAdresse());
        dto.setComplement(fournisseur.getComplement());
        dto.setCodePostal(fournisseur.getCodePostal());
        dto.setVille(fournisseur.getVille());
        dto.setRegion(fournisseur.getRegion());
        dto.setPays(fournisseur.getPays());
        dto.setStatistiques1(fournisseur.getStatistiques1());
        dto.setStatistiques2(fournisseur.getStatistiques2());
        dto.setStatistiques3(fournisseur.getStatistiques3());
        dto.setType(fournisseur.getType().name());
        dto.setModePaiementId(fournisseur.getModePaiement().getId());
        dto.setEcheance(fournisseur.getEcheance());
        dto.setIban(fournisseur.getIban());
        dto.setBic(fournisseur.getBic());
        dto.setContactEmail(fournisseur.getContactEmail());
        dto.setContactFonction(fournisseur.getContactFonction());
        dto.setContactPrenoms(fournisseur.getContactPrenoms());
        dto.setContactNom(fournisseur.getContactNom());
        dto.setDeviseId(fournisseur.getDevise().getId());
        return dto;
    }

    private void mapToEntity(FournisseurDto dto, FournisseurM entity) {
        entity.setCodeFournisseur(dto.getCodeFournisseur());
        entity.setIntituleFournisseur(dto.getIntituleFournisseur());
        entity.setNomContact(dto.getNomContact());
        entity.setTelephone(dto.getTelephone());
        entity.setTelecopie(dto.getTelecopie());
        entity.setEmail(dto.getEmail());
        entity.setSiret(dto.getSiret());
        entity.setNTvaIntracommunautaire(dto.getNTvaIntracommunautaire());
        entity.setAdresse(dto.getAdresse());
        entity.setComplement(dto.getComplement());
        entity.setCodePostal(dto.getCodePostal());
        entity.setVille(dto.getVille());
        entity.setRegion(dto.getRegion());
        entity.setPays(dto.getPays());
        entity.setStatistiques1(dto.getStatistiques1());
        entity.setStatistiques2(dto.getStatistiques2());
        entity.setStatistiques3(dto.getStatistiques3());
        entity.setEcheance(dto.getEcheance());
        entity.setIban(dto.getIban());
        entity.setBic(dto.getBic());
        entity.setContactEmail(dto.getContactEmail());
        entity.setContactFonction(dto.getContactFonction());
        entity.setContactPrenoms(dto.getContactPrenoms());
        entity.setContactNom(dto.getContactNom());

        if (dto.getModePaiementId() != null) {
            ModePaiement modePaiement = modePaiementRepository.findFirstByIdAndDeleted(dto.getModePaiementId(), Deletion.NO)
                    .orElseThrow(() -> new NoSuchElementException("mode non trouvé"));
            entity.setModePaiement(modePaiement);
        } else {
            entity.setModePaiement(null);
        }

//        if (dto.getDeviseId() != null) {
//            Devise devise = deviseRepository.findFirstByIdAndDeleted(dto.getDeviseId(), Deletion.NO)
//                    .orElseThrow(() -> new NoSuchElementException("la devise non trouvée"));
//            entity.setDevise(devise);
//        } else {
//            entity.setDevise(null);
//        }
    }
}
