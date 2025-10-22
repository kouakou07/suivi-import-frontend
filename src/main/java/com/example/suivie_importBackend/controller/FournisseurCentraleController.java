package com.example.suivie_importBackend.controller;

import com.example.suivie_importBackend.dto.FournisseurCentraleAssociationDto;
import com.example.suivie_importBackend.dto.FournisseurCentraleDto;
import com.example.suivie_importBackend.models.FournisseurCentraleM;
import com.example.suivie_importBackend.service.FournisseurCentraleService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class FournisseurCentraleController {

    private final FournisseurCentraleService fournisseurCentraleService;

    public FournisseurCentraleController(FournisseurCentraleService fournisseurCentraleService) {
        this.fournisseurCentraleService = fournisseurCentraleService;
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/centrale/{centraleId}/fournisseurs/associer")
    public ResponseEntity<List<FournisseurCentraleM>> associerFournisseursACentrale(
            @PathVariable Long centraleId,
            @RequestBody FournisseurCentraleAssociationDto request) {
        if (request.getFournisseursIds() == null || request.getFournisseursIds().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        List<FournisseurCentraleM> associations =
                fournisseurCentraleService.associerFournisseursACentrale(centraleId, request.getFournisseursIds());
        return ResponseEntity.ok(associations);
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/centrale/fournisseurs/{idCentrale}")
    public ResponseEntity<FournisseurCentraleDto> getFournisseursByCentrale(
            @PathVariable("idCentrale") Long idCentrale
    ) {
        FournisseurCentraleDto dto = fournisseurCentraleService.getFournisseursByCentrale(idCentrale);
        return ResponseEntity.ok(dto);
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/centrale/fournisseurs/supprimer/{associationId}")
    public ResponseEntity<Void> supprimerAssociation(@PathVariable Long associationId) {
        fournisseurCentraleService.supprimerAssociation(associationId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/centrales/fournisseurs/{idFournisseur}")
    public ResponseEntity<Void> supprimerFournisseurAssocie(
            @PathVariable Long idFournisseur) {
        fournisseurCentraleService.supprimerFournisseurAssocie(idFournisseur);
        return ResponseEntity.noContent().build();
    }

}
