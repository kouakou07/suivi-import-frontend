package com.example.suivie_importBackend.controller;

import com.example.suivie_importBackend.Facade.IncotermFacade;
import com.example.suivie_importBackend.dto.IncotermDto;
import com.example.suivie_importBackend.vo.IncotermVO;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class IncotermController {
    private final IncotermFacade incotermFacade;

    public IncotermController(IncotermFacade incotermFacade) {
        this.incotermFacade = incotermFacade;
    }

    @PostMapping("/incoterms/ajouter")
    public ResponseEntity<IncotermDto> enregistrer(@RequestBody IncotermVO vo) {
        return ResponseEntity.ok(incotermFacade.enregistrer(vo));
    }

    @GetMapping("/incoterms/liste")
    public ResponseEntity<List<IncotermDto>> lister() {
        return ResponseEntity.ok(incotermFacade.listerTous());
    }

    @GetMapping("/incoterms/liste/{page}")
    public ResponseEntity<Page<IncotermDto>> listerAvecPagination(@RequestParam(defaultValue = "0") int page) {
        return ResponseEntity.ok(incotermFacade.listerTousAvecPagination(page));
    }

    @PostMapping("/incoterms/editer/{id}")
    public ResponseEntity<IncotermDto> modifier(@PathVariable Long id, @RequestBody IncotermVO vo) {
        return ResponseEntity.ok(incotermFacade.modifier(id, vo));
    }

    @PostMapping("/incoterms/supprimer/{id}")
    public ResponseEntity<Void> supprimer(@PathVariable Long id) {
        boolean deleted = incotermFacade.supprimer(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
