package com.example.suivie_importBackend.controller;

import com.example.suivie_importBackend.dto.AcheteurDto;
import com.example.suivie_importBackend.service.AcheteurService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AcheteurController {

    private final AcheteurService acheteurService;

    public AcheteurController(AcheteurService acheteurService) {
        this.acheteurService = acheteurService;
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/acheteur/enregistrer")
    public ResponseEntity<AcheteurDto> creerAcheteur(@RequestBody AcheteurDto dto) {
        AcheteurDto created = acheteurService.creerAcheteur(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/acheteur/{id}")
    public ResponseEntity<AcheteurDto> detailAcheteur(@PathVariable Long id) {
        return acheteurService.detailAcheteur(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/acheteur/listeAcheteurs")
    public ResponseEntity<Page<AcheteurDto>> listeAcheteurs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        Page<AcheteurDto> pageResult = acheteurService.recupererTousLesAcheteursAvecPagination(page, size);
        return ResponseEntity.ok(pageResult);
    }

    @PutMapping("/acheteur/modifier/{id}")
    public ResponseEntity<AcheteurDto> modifierAcheteur(@PathVariable Long id, @RequestBody AcheteurDto dto) {
        try {
            AcheteurDto updated = acheteurService.modifierAcheteur(id, dto);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Soft delete un acheteur
    @DeleteMapping("/acheteur/delete/{id}")
    public ResponseEntity<Void> supprimerAcheteur(@PathVariable Long id) {
        acheteurService.supprimerAcheteur(id);
        return ResponseEntity.noContent().build();
    }
}