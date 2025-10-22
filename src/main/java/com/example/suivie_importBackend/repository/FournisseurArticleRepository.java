package com.example.suivie_importBackend.repository;

import com.example.suivie_importBackend.models.FournisseurArticleM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface FournisseurArticleRepository extends JpaRepository<FournisseurArticleM, Long>  {

//    List<FournisseurArticleM> findByIdAndDeleted(Long id, Boolean deletion);
    List<FournisseurArticleM> findByIdArticleAndDeleted(Long IdArticle, Boolean deletion);

    Optional<FournisseurArticleM> findByIdAndDeleted(Long idFournisseurArticle, Boolean deletion);

    boolean existsByIdArticleAndIdFournisseurAndDeleted(Long idArticle, Long idFournisseur, Boolean deleted);

}
