package com.example.suivie_importBackend.controller;

import com.example.suivie_importBackend.Enum.Departement;
import com.example.suivie_importBackend.Enum.FamilleCentrale;
import com.example.suivie_importBackend.Enum.UniteVente;
import com.example.suivie_importBackend.dto.ArticleDto;
import com.example.suivie_importBackend.dto.FournisseurDto;
import com.example.suivie_importBackend.service.ArticleService;
import com.example.suivie_importBackend.vo.ArticleVO;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/article/creer")
    public ResponseEntity<ArticleDto> creerArticle(@RequestBody ArticleDto articleDto) {
        ArticleDto nouveauArticle = articleService.creerArticle(articleDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nouveauArticle);
    }

    @Secured({"ROLE_ADMIN", "ROLE_VALIDATEUR", "ROLE_USER"})
    @GetMapping("/article/liste-article")
    public List<ArticleDto> recupererTousLesArticles() {
        return articleService.recupererTousLesArticles();
    }

    @Secured({"ROLE_ADMIN", "ROLE_VALIDATEUR", "ROLE_USER"})
    @GetMapping("/articles/liste-articles")
    public List<ArticleVO> recupererTousLesArticlesPourAffichage() {
        return articleService.recupererArticlesPourAffichage();
    }

    @Secured({"ROLE_ADMIN", "ROLE_VALIDATEUR", "ROLE_USER"})
    @GetMapping("/article/liste/{page}")
    public Page<ArticleDto> recupererTousLesArticleAvecPagination(@PathVariable Integer page) {
        return articleService.recupererTousLesArticlesAvecPagination(page);
    }

    @Secured({"ROLE_ADMIN", "ROLE_VALIDATEUR", "ROLE_USER"})
    @PostMapping("/article/edition")
    public ArticleDto mettreAJourArticle(
            @RequestBody ArticleDto articleDto) {
        return articleService.mettreAJourArticle(articleDto);
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/article/supprimer/{id}")
    public ResponseEntity<String> supprimerArticle(@PathVariable Long id) {
        String message = articleService.supprimerArticle(id);
        return ResponseEntity.ok(message);
    }

//    @Secured("ROLE_ADMIN")
    @GetMapping("/article/info/{articleId}")
    public ResponseEntity<ArticleDto> getArticleById(@PathVariable Long articleId) {
        ArticleDto articleDto = articleService.detailArticle(articleId);
        return ResponseEntity.ok(articleDto);
    }

    @Secured({"ROLE_ADMIN", "ROLE_VALIDATEUR", "ROLE_USER"})
    @GetMapping("/article/code/{valeur}")
    public Page<ArticleDto> rechercherArticles(
            @RequestParam(name = "valeur", required = false) String valeur,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "20") int size
    ) {
        return articleService.rechercherArticle(valeur, page, size);
    }

    @Secured({"ROLE_ADMIN", "ROLE_VALIDATEUR", "ROLE_USER"})
    @GetMapping("/article/departements")
    public Departement[] getDepartements() {
        return Departement.values();
    }

    @Secured({"ROLE_ADMIN", "ROLE_VALIDATEUR", "ROLE_USER"})
    @GetMapping("/article/familles")
    public FamilleCentrale[] getFamilles() {
        return FamilleCentrale.values();
    }

    @Secured({"ROLE_ADMIN", "ROLE_VALIDATEUR", "ROLE_USER"})
    @GetMapping("/article/unites")
    public UniteVente[] getUnites() {
        return UniteVente.values();
    }
}
