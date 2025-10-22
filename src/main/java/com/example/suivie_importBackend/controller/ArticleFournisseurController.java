package com.example.suivie_importBackend.controller;

import com.example.suivie_importBackend.dto.FournisseurArticleAssociationDto;
import com.example.suivie_importBackend.dto.FournisseurArticleAssociationResponseDto;
import com.example.suivie_importBackend.dto.FournisseurArticleDto;
import com.example.suivie_importBackend.models.FournisseurArticleM;
import com.example.suivie_importBackend.service.FournisseurArticleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ArticleFournisseurController {

    private FournisseurArticleService fournisseurArticleService;
    public ArticleFournisseurController(FournisseurArticleService fournisseurArticleService) {
        this.fournisseurArticleService = fournisseurArticleService;
    }

    @Secured({"ROLE_ADMIN", "ROLE_VALIDATEUR", "ROLE_USER"})
    @PostMapping("/article/{articleId}/fournisseurs/associer")
    public ResponseEntity<List<FournisseurArticleM>> associerFournisseurs(
            @PathVariable Long articleId,
            @RequestBody FournisseurArticleAssociationDto request) {
        if (request.getFournisseursIds() == null || request.getFournisseursIds().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        List<FournisseurArticleM> associations = fournisseurArticleService.associerFournisseursArticle(
                articleId,
                request.getFournisseursIds()
        );
        return new ResponseEntity<>(associations, HttpStatus.OK);
    }

    @Secured({"ROLE_ADMIN", "ROLE_VALIDATEUR", "ROLE_USER"})
    @GetMapping("/article/fournisseurs/{articleId}")
    public ResponseEntity<FournisseurArticleDto> getFournisseursByArticle(@PathVariable Long articleId) {
        FournisseurArticleDto dto = fournisseurArticleService.getFournisseursByArticle(articleId);
        return ResponseEntity.ok(dto);
    }

    @Secured({"ROLE_ADMIN", "ROLE_VALIDATEUR", "ROLE_USER"})
    @PostMapping("/article/fournisseurs/supprimer/{associationId}")
    public void supprimer(@PathVariable Long associationId){
        fournisseurArticleService.supprimer(associationId);
    }

}
