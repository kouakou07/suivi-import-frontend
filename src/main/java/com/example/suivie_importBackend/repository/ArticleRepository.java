package com.example.suivie_importBackend.repository;

import com.example.suivie_importBackend.Enum.Deletion;
import com.example.suivie_importBackend.dto.ArticleDto;
import com.example.suivie_importBackend.models.ArticleM;
import com.example.suivie_importBackend.models.FournisseurM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<ArticleM, Long> {

    Optional<ArticleM> findByIdAndDeleted(Long idFournisseur, Boolean deletion);

    Page<ArticleM> findAllByDeleted(Boolean deletion, Pageable page);

    List<ArticleM> findAllByDeleted(Boolean deletion);

    Optional<ArticleM> findFirstByIdAndDeleted(Long id, Boolean deletion);

    Page<ArticleM> findByCodeFamilleContainingIgnoreCaseOrLibelleFamilleContainingIgnoreCaseAndDeleted(
            String codeFamille,
            String libelleFamille,
            Boolean deleted,
            Pageable page
    );

    @Query("""
    SELECT new com.example.suivie_importBackend.dto.ArticleDto(
        a.id,
        a.referenceArticle,
        a.designationArticle,
        a.codeFamille,
        a.libelleFamille,
        a.afRefFourniss,
        f.codeFournisseur,
        f.intituleFournisseur,
        a.suiviStock,
        a.familleCentrale.id,
        a.departement.id,
        a.uniteVente.id
    )
    FROM ArticleM a
    LEFT JOIN a.fournisseurId f
    WHERE a.deleted = :deleted
""")
    List<ArticleDto> findAllArticlesWithFournisseur(@Param("deleted") Boolean deleted);

}
