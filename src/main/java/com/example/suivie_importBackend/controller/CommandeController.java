package com.example.suivie_importBackend.controller;

import com.example.suivie_importBackend.dto.CommandeDto;
import com.example.suivie_importBackend.dto.CommandeListDto;
import com.example.suivie_importBackend.dto.LigneCommandeDto;
import com.example.suivie_importBackend.models.CommandeM;
import com.example.suivie_importBackend.models.LigneCommande;
import com.example.suivie_importBackend.service.CommandeService;
import com.example.suivie_importBackend.vo.CommandeVO;
import com.example.suivie_importBackend.vo.LigneCommandeVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/commandes")
public class CommandeController {

    private final CommandeService commandeService;

    public CommandeController(CommandeService commandeService) {
        this.commandeService = commandeService;
    }

    @Secured({"ROLE_ADMIN", "ROLE_VALIDATEUR", "ROLE_USER"})
    @PostMapping("/cree")
    public ResponseEntity<CommandeVO> creerCommande(@RequestBody CommandeDto dto) {
        CommandeVO commande = commandeService.creerCommande(dto);
        return ResponseEntity.ok(commande);
    }

    @Secured({"ROLE_ADMIN", "ROLE_VALIDATEUR", "ROLE_USER"})
    @PostMapping("/lignes/{commandeId}")
    public CommandeVO ajouterLignes(@PathVariable Long commandeId,
                                    @RequestBody List<LigneCommandeDto> lignes) {
        return commandeService.ajouterLignesCommande(commandeId, lignes);
    }

    @Secured({"ROLE_ADMIN", "ROLE_VALIDATEUR", "ROLE_USER"})
    @PutMapping("/modifier/{id}")
    public ResponseEntity<CommandeVO> modifierCommande(@PathVariable Long id, @RequestBody CommandeDto dto) {
        CommandeVO commande = commandeService.modifierCommande(id, dto);
        return ResponseEntity.ok(commande);
    }

    @Secured({"ROLE_ADMIN", "ROLE_VALIDATEUR", "ROLE_USER"})
    @GetMapping("/detail/{id}")
    public ResponseEntity<CommandeVO> getCommande(@PathVariable Long id) {
        CommandeVO commande = commandeService.DetailCommandeParId(id);
        return ResponseEntity.ok(commande);
    }

    @Secured({"ROLE_ADMIN", "ROLE_VALIDATEUR", "ROLE_USER"})
    @GetMapping("/listeCommande")
    public ResponseEntity<List<CommandeVO>> listerCommandes() {
        List<CommandeVO> commandes = commandeService.listerCommandes();
        return ResponseEntity.ok(commandes);
    }

    @Secured({"ROLE_ADMIN", "ROLE_VALIDATEUR", "ROLE_USER"})
    @GetMapping("/listeCommandeAvecPagination")
    public ResponseEntity<Page<CommandeListDto>> getCommandes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<CommandeListDto> commandes = commandeService.listerCommandesAvecPagination(pageable);
        return ResponseEntity.ok(commandes);
    }

    @Secured({"ROLE_ADMIN", "ROLE_VALIDATEUR", "ROLE_USER"})
    @PostMapping("/supprimer/{id}")
    public ResponseEntity<Void> supprimerCommande(@PathVariable Long id) {
        commandeService.supprimerCommande(id);
        return ResponseEntity.noContent().build();
    }

    @Secured({"ROLE_ADMIN", "ROLE_VALIDATEUR", "ROLE_USER"})
    @GetMapping("/{commandeId}/ligneCommande")
    public ResponseEntity<List<LigneCommandeVO>> lignes(@PathVariable Long commandeId) {
        List<LigneCommandeVO> lignes = commandeService.lignesDeCommande(commandeId);
        return ResponseEntity.ok(lignes);
    }

}
