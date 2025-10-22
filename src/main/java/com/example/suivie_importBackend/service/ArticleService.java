package com.example.suivie_importBackend.service;

import com.example.suivie_importBackend.Enum.Deletion;
import com.example.suivie_importBackend.dto.ArticleDto;
import com.example.suivie_importBackend.dto.FournisseurDto;
import com.example.suivie_importBackend.models.*;
import com.example.suivie_importBackend.repository.*;
import com.example.suivie_importBackend.vo.ArticleVO;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final FournisseurRepository fournisseurRepository;
    private DepartementRepository departementRepository;
    private FamilleCentraleRepository familleCentraleRepository;
    private UniteVenteRepository uniteVenteRepository;

    public ArticleService(ArticleRepository articleRepository, FournisseurRepository fournisseurRepository, DepartementRepository departementRepository, FamilleCentraleRepository familleCentraleRepository, UniteVenteRepository uniteVenteRepository) {
        this.articleRepository = articleRepository;
        this.fournisseurRepository = fournisseurRepository;
        this.departementRepository = departementRepository;
        this.familleCentraleRepository = familleCentraleRepository;
        this.uniteVenteRepository = uniteVenteRepository;
    }

    private FournisseurDto mapToFournisseurDto(FournisseurM fournisseur) {
        if (fournisseur == null) return null;
        return FournisseurDto.builder()
                .id(fournisseur.getId())
                .codeFournisseur(fournisseur.getCodeFournisseur())
                .intituleFournisseur(fournisseur.getIntituleFournisseur())
                .build();
    }

    public ArticleDto mapToDto(ArticleM article) {

        if (article == null) return null;
        FournisseurM fournisseur = article.getFournisseurId();
        String codeFournisseur = fournisseur != null ? fournisseur.getCodeFournisseur() : null;
        String intituleFournisseur = fournisseur != null ? fournisseur.getIntituleFournisseur() : null;
        Departement departement = article.getDepartement();
        FamilleCentrale familleCentrale = article.getFamilleCentrale();
        UniteVente uniteVente = article.getUniteVente();

        return ArticleDto.builder()
                .id(article.getId())
                .referenceArticle(article.getReferenceArticle())
                .designationArticle(article.getDesignationArticle())
                .codeFamille(article.getCodeFamille())
                .libelleFamille(article.getLibelleFamille())
                .afRefFourniss(article.getAfRefFourniss())
                .codeFournisseur(codeFournisseur)
                .intituleFournisseur(intituleFournisseur)
                .suiviStock(article.getSuiviStock())
                .familleCentraleId(familleCentrale != null ? familleCentrale.getId() : null)
                .departementId(departement != null ? departement.getId() : null)
                .uniteVenteId(uniteVente != null ? uniteVente.getId() : null)
                .build();
    }

    public List<ArticleDto> recupererTousLesArticles() {
        return articleRepository.findAllArticlesWithFournisseur(Deletion.NO);
    }

    public List<ArticleVO> recupererArticlesPourAffichage() {
        return articleRepository.findAllByDeleted(Deletion.NO)
                .stream()
                .map(this::mapToDto)
                .map(this::mapToVO)
                .collect(Collectors.toList());
    }

    public Page<ArticleDto> recupererTousLesArticlesAvecPagination(int page) {
        Page<ArticleM> articlesPage = articleRepository.findAllByDeleted(Deletion.NO, PageRequest.of(page, 20));
        return articlesPage.map(this::mapToDto);
    }

    public String supprimerArticle(Long id) {
        Optional<ArticleM> optionalArticle = articleRepository.findFirstByIdAndDeleted(id, Deletion.NO);
        if (optionalArticle.isPresent()) {
            ArticleM recupererArticle = optionalArticle.get();
            recupererArticle.setDeleted(Deletion.YES);
            articleRepository.save(recupererArticle);
            return "Article supprimé avec succès";
        } else {
            throw new RuntimeException("Article non trouvé ou déjà supprimé");
        }
    }

    public ArticleDto creerArticle(ArticleDto articleDto) {
        ArticleM article = new ArticleM();
        mapToEntity(articleDto, article);
        article.setDeleted(Deletion.NO);
        article.setDateCreation(new Date());
        ArticleM articleCree = articleRepository.save(article);
        return mapToDto(articleCree);
    }

    public ArticleDto mettreAJourArticle(ArticleDto articleDto) {
        Optional<ArticleM> optionalArticle = articleRepository.findFirstByIdAndDeleted(articleDto.getId(), Deletion.NO);
        if (optionalArticle.isPresent()) {
            ArticleM article = optionalArticle.get();
            mapToEntity(articleDto, article);
            ArticleM ArticleMiseAJour = articleRepository.save(article);
            return mapToDto(ArticleMiseAJour);
        }
        throw new NoSuchElementException("Fournisseur non trouvé");
    }

    private void mapToEntity(ArticleDto dto, ArticleM entity) {

        Departement departement = departementRepository
                .findFirstByIdAndDeleted(dto.getDepartementId(), Deletion.NO)
                .orElseThrow(() -> new RuntimeException("Le département n’a pas été trouvé"));

        FamilleCentrale familleCentrale = familleCentraleRepository
                .findFirstByIdAndDeleted(dto.getFamilleCentraleId(), Deletion.NO)
                .orElseThrow(() -> new RuntimeException("Famille centrale non trouvée"));

        UniteVente uniteVente = uniteVenteRepository
                .findFirstByIdAndDeleted(dto.getUniteVenteId(), Deletion.NO)
                .orElseThrow(() -> new RuntimeException("Unité de vente non trouvée"));

        FournisseurM fournisseur = fournisseurRepository
                .findFirstByIntituleFournisseurAndDeleted(dto.getIntituleFournisseur(), Deletion.NO)
                .orElseThrow(() -> new RuntimeException("Fournisseur non trouvé : " + dto.getIntituleFournisseur()));

        entity.setId(dto.getId());
        entity.setCodeFamille(dto.getCodeFamille());
        entity.setLibelleFamille(dto.getLibelleFamille());
        entity.setReferenceArticle(dto.getReferenceArticle());
        entity.setAfRefFourniss(dto.getAfRefFourniss());
        entity.setDesignationArticle(dto.getDesignationArticle());
        entity.setSuiviStock(dto.getSuiviStock());
        entity.setFournisseurId(fournisseur);
        entity.setDepartement(departement);
        entity.setFamilleCentrale(familleCentrale);
        entity.setUniteVente(uniteVente);
    }

    public ArticleVO mapToVO(ArticleDto articleDto) {
        if (articleDto == null) return null;
        String nomDepartement = articleDto.getDepartementId() != null ? articleDto.getDepartementId().toString() : null;
        String libelleUniteVente = articleDto.getUniteVenteId() != null ? articleDto.getUniteVenteId().toString() : null;
        String familleCentrale = articleDto.getFamilleCentraleId() != null ? articleDto.getFamilleCentraleId().toString() : null;

        return ArticleVO.builder()
                .id(articleDto.getId())
                .referenceArticle(articleDto.getReferenceArticle())
                .designationArticle(articleDto.getDesignationArticle())
                .codeFamille(articleDto.getCodeFamille())
                .libelleFamille(articleDto.getLibelleFamille())
                .afRefFourniss(articleDto.getAfRefFourniss())
                .codeFournisseur(articleDto.getCodeFournisseur())
                .intituleFournisseur(articleDto.getIntituleFournisseur())
                .suiviStock(articleDto.getSuiviStock())
                .familleCentrale(familleCentrale)
                .libelleUniteVente(libelleUniteVente)
                .nomDepartement(nomDepartement)
                .build();
    }

    public ArticleDto detailArticle(Long articleId) {
        return articleRepository.findFirstByIdAndDeleted(articleId, Deletion.NO)
                .map(this::mapToDto)
                .orElseThrow(() -> new NoSuchElementException("article non trouvé"));
    }

    public Page<ArticleDto> rechercherArticle(String valeur, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("codeFamille").ascending());

        if (valeur == null || valeur.isEmpty()) {
            return articleRepository.findAllByDeleted(Deletion.NO, pageRequest)
                    .map(this::mapToDto);
        }
        return articleRepository
                .findByCodeFamilleContainingIgnoreCaseOrLibelleFamilleContainingIgnoreCaseAndDeleted(
                        valeur, valeur, Deletion.NO, pageRequest
                )
                .map(this::mapToDto);
    }
}
