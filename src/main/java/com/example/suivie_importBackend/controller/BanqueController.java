package com.example.suivie_importBackend.controller;

import com.example.suivie_importBackend.Facade.BanqueFacade;
import com.example.suivie_importBackend.dto.BanqueDto;
import com.example.suivie_importBackend.vo.BanqueVO;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BanqueController {

    private final BanqueFacade banqueFacade;

    public BanqueController(BanqueFacade banqueFacade) {
        this.banqueFacade = banqueFacade;
    }

    @PostMapping("/banques/ajouter")
    public ResponseEntity<BanqueDto> enregistrer(@RequestBody BanqueVO banqueVO) {
        return ResponseEntity.ok(banqueFacade.enregistrer(banqueVO));
    }

    @GetMapping("/banques/liste")
    public ResponseEntity<List<BanqueDto>> lister() {
        return ResponseEntity.ok(banqueFacade.listerTous());
    }

    @GetMapping("/banques/liste/{page}")
    public ResponseEntity<Page<BanqueDto>> listerAvecPagination(
            @RequestParam(defaultValue = "0") int page
    ) {
        return ResponseEntity.ok(banqueFacade.listerTousAvecPagination(page));
    }

    @PostMapping("/banques/editer/{id}")
    public ResponseEntity<BanqueDto> modifier(@PathVariable Long id, @RequestBody BanqueVO vo) {
        return ResponseEntity.ok(banqueFacade.modifier(id, vo));
    }

    @PostMapping("/banques/supprimer/{id}")
    public ResponseEntity<Void> supprimer(@PathVariable Long id) {
        boolean deleted = banqueFacade.supprimer(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
