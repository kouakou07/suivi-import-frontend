package com.example.suivie_importBackend.controller;

import com.example.suivie_importBackend.dto.FournisseurDto;
import com.example.suivie_importBackend.service.FournisseurService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@RestController
@RequestMapping("/api")
public class FournisseurController {

    private final FournisseurService fournisseurService;

    public FournisseurController(FournisseurService fournisseurService) {
        this.fournisseurService = fournisseurService;
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/fournisseur/creer")
    public ResponseEntity<FournisseurDto> creerFournisseur(@RequestBody FournisseurDto fournisseurDto) {
        FournisseurDto nouveauFournisseur = fournisseurService.creerFournisseur(fournisseurDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nouveauFournisseur);
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/fournisseur/liste-fournisseur")
    public List<FournisseurDto> recupererTousLesFournisseurs() {
        return fournisseurService.recupererTousLesFournisseurs();
    }

    @Secured({"ROLE_ADMIN", "ROLE_VALIDATEUR", "ROLE_USER"})
    @GetMapping("/fournisseur/liste/{page}")
    public ResponseEntity<Page<FournisseurDto>> recupererTousLesFournisseursAvecPagination(@PathVariable int page) {
        Page<FournisseurDto> pageResult = fournisseurService.recupererTousLesFournisseursAvecPagination(page);
        return ResponseEntity.ok(pageResult);
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/fournisseur/edition")
    public FournisseurDto mettreAJourFournisseur(
            @RequestBody FournisseurDto fournisseurDto) {
        return fournisseurService.mettreAJourFournisseur(fournisseurDto);
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/fournisseur/supprimer/{id}")
    public ResponseEntity<String> supprimerFournisseur(@PathVariable Long id) {
        String message = fournisseurService.supprimerFournisseur(id);
        return ResponseEntity.ok(message);
    }

    @Secured({"ROLE_ADMIN", "ROLE_VALIDATEUR", "ROLE_USER"})
    @GetMapping("/fournisseur/info/{fournisseurId}")
    public ResponseEntity<FournisseurDto> getFournisseurById(@PathVariable Long fournisseurId) {
        FournisseurDto fournisseurDto = fournisseurService.detailFournisseur(fournisseurId);
        return ResponseEntity.ok(fournisseurDto);
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/code/{valeur}")
    public ResponseEntity<FournisseurDto> getFournisseurParCodeOuIntitule(@PathVariable String valeur) {
        FournisseurDto dto = fournisseurService.recupererFournisseurParCodeOuIntitule(valeur);
        if (dto != null) {
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/fournisseurs")
    public Page<FournisseurDto> rechercherFournisseurs(
            @RequestParam(name = "codeFournisseur", required = false) String codeFournisseur,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "20") int size
    ) {
        return fournisseurService.rechercherFournisseursParCode(codeFournisseur, page, size);
    }

    @PostMapping("/fournisseur/upload-excel")
    public ResponseEntity<String> uploadExcel(@RequestParam("file") MultipartFile file) {
        fournisseurService.importerDepuisExcel(file);
        return ResponseEntity.ok("Importation réussie !");
    }

}
