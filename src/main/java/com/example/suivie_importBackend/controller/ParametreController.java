package com.example.suivie_importBackend.controller;


import com.example.suivie_importBackend.dto.*;
import com.example.suivie_importBackend.models.Devise;
import com.example.suivie_importBackend.models.FamilleCentrale;
import com.example.suivie_importBackend.models.ModeEnvoi;
import com.example.suivie_importBackend.models.Pays;
import com.example.suivie_importBackend.service.ParametreService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ParametreController {

    ParametreService parametreService;

    public ParametreController(ParametreService parametreService) {
        this.parametreService = parametreService;
    }

    @Secured({"ROLE_ADMIN", "ROLE_VALIDATEUR", "ROLE_USER"})
    @PostMapping("/pays/ajouter")
    public ResponseEntity<Pays> creerPays(@RequestBody Pays pays) {
        return parametreService.creerPays(pays);
    }

    @Secured({"ROLE_ADMIN", "ROLE_VALIDATEUR", "ROLE_USER"})
    @PutMapping("/pays/modifier")
    public ResponseEntity<PaysDto> modifierPays(@RequestBody PaysDto paysDto) {
        return ResponseEntity.ok(parametreService.mettreAJourPays(paysDto));
    }

    @Secured({"ROLE_ADMIN", "ROLE_VALIDATEUR", "ROLE_USER"})
    @DeleteMapping("/pays/supprimer/{id}")
    public ResponseEntity<Void> supprimerPays(@PathVariable Long id) {
        boolean deleted = parametreService.supprimerPays(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @Secured({"ROLE_ADMIN", "ROLE_VALIDATEUR", "ROLE_USER"})
    @GetMapping("/pays/liste")
    public ResponseEntity<Page<PaysDtoList>> recupererPays(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<PaysDtoList> paysPage = parametreService.recupererPays(pageable);
        return ResponseEntity.ok(paysPage);
    }

    @Secured({"ROLE_ADMIN", "ROLE_VALIDATEUR", "ROLE_USER"})
    @PostMapping("/modeEnvoi/creer")
    public ResponseEntity<ModeEnvoi> creerModeEnvoi(@RequestBody ModeEnvoi modeEnvoi) {
        ModeEnvoi enregistreModeEnvoi = parametreService.creerModeEnvoi(modeEnvoi);
        return ResponseEntity.status(HttpStatus.CREATED).body(enregistreModeEnvoi);
    }

    @Secured({"ROLE_ADMIN", "ROLE_VALIDATEUR", "ROLE_USER"})
    @PutMapping("/modeEnvoi/modifier")
    public ResponseEntity<ModeEnvoiDto> modifierModeEnvoi(@RequestBody ModeEnvoiDto modeEnvoiDto) {
        ModeEnvoiDto modeMisAJour = parametreService.mettreAJourModeEnvoi(modeEnvoiDto);
        return ResponseEntity.ok(modeMisAJour);
    }

    @Secured({"ROLE_ADMIN", "ROLE_VALIDATEUR", "ROLE_USER"})
    @GetMapping("/modeEnvoi/liste")
    public ResponseEntity<Page<ModeEnvoiListDto>> recupererModeEnvoi(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ModeEnvoiListDto> modePage = parametreService.recupererModeEnvoi(pageable);
        return ResponseEntity.ok(modePage);
    }

    @Secured({"ROLE_ADMIN", "ROLE_VALIDATEUR", "ROLE_USER"})
    @DeleteMapping("/modeEnvoi/supprimer/{id}")
    public ResponseEntity<Void> supprimerModeEnvoi(@PathVariable Long id) {
        boolean deleted = parametreService.supprimerModeEnvoi(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @Secured({"ROLE_ADMIN", "ROLE_VALIDATEUR", "ROLE_USER"})
    @PostMapping("/devises/ajouter")
    public ResponseEntity<Devise> creerDevise(@RequestBody DeviseDto devise) {
        Devise enregistreDevise = parametreService.creerDevise(devise);
        return ResponseEntity.status(HttpStatus.CREATED).body(enregistreDevise);
    }

    @Secured({"ROLE_ADMIN", "ROLE_VALIDATEUR", "ROLE_USER"})
    @PostMapping("/devises/editer/{id}")
    public ResponseEntity<DeviseDto> modifierDevise(@RequestBody DeviseDto deviseDto) {
        DeviseDto deviseMisAJour = parametreService.mettreAJourDevise(deviseDto);
        return ResponseEntity.ok(deviseMisAJour);
    }

    @Secured({"ROLE_ADMIN", "ROLE_VALIDATEUR", "ROLE_USER"})
    @GetMapping("/devises/liste/{page}")
    public ResponseEntity<Page<DeviseDtoList>> recupererDevise(
            @RequestParam(defaultValue = "0") int page
    ) {
        Page<DeviseDtoList> devisePage = parametreService.recupererDevise(page);
        return ResponseEntity.ok(devisePage);
    }

    @Secured({"ROLE_ADMIN", "ROLE_VALIDATEUR", "ROLE_USER"})
    @PostMapping("/devises/supprimer/{id}")
    public ResponseEntity<Void> supprimerDevise(@PathVariable Long id) {
        boolean deleted = parametreService.supprimerDevise(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @Secured({"ROLE_ADMIN", "ROLE_VALIDATEUR", "ROLE_USER"})
    @PostMapping("/familleCentrale/creer")
    public ResponseEntity<FamilleCentrale> creerFamilleCentrale(@RequestBody FamilleCentrale familleCentrale) {
        FamilleCentrale enregistreFamille1 = parametreService.creerFamilleCentrale(familleCentrale);
        return ResponseEntity.status(HttpStatus.CREATED).body(enregistreFamille1);
    }

    @Secured({"ROLE_ADMIN", "ROLE_VALIDATEUR", "ROLE_USER"})
    @PutMapping("/familleCentrale/modifier")
    public ResponseEntity<FamilleCentraleDto> modifierFamilleCentrale(@RequestBody FamilleCentraleDto familleCentraleDto) {
        FamilleCentraleDto familleMisAJour = parametreService.mettreAJourFamilleCentrale(familleCentraleDto);
        return ResponseEntity.ok(familleMisAJour);
    }

    @Secured({"ROLE_ADMIN", "ROLE_VALIDATEUR", "ROLE_USER"})
    @GetMapping("/familleCentrale/liste")
    public ResponseEntity<Page<FamilleCentraleDtoList>> recupererFamilleCentrale(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<FamilleCentraleDtoList> familleCentralePage = parametreService.recupererFamilleCentrale(pageable);
        return ResponseEntity.ok(familleCentralePage);
    }

    @Secured({"ROLE_ADMIN", "ROLE_VALIDATEUR", "ROLE_USER"})
    @DeleteMapping("/familleCentrale/supprimer/{id}")
    public ResponseEntity<Void> supprimerFamilleCentrale(@PathVariable Long id) {
        boolean deleted = parametreService.supprimerFamilleCentrale(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
