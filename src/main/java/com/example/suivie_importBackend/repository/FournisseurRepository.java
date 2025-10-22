package com.example.suivie_importBackend.repository;

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
public interface FournisseurRepository extends JpaRepository<FournisseurM, Long> {

    List<FournisseurM> findAllByDeleted(Boolean deletion);

    Page<FournisseurM> findAllByDeleted(Boolean deletion, Pageable page);

    Optional<FournisseurM> findFirstByIdAndDeleted(Long id, Boolean deletion);

//    FournisseurM  findByCodeFournisseurAndDeleted(String codeFournisseur, Boolean deletion);

    Optional<FournisseurM> findByIdAndDeleted(Long id, Boolean deletion);

    List<FournisseurM> findAllByIdInAndDeleted(List<Long> fournisseurIds, Boolean deletion);

    @Query("SELECT f FROM FournisseurM f WHERE f.deleted = :deleted ORDER BY f.codeFournisseur ASC")
    Page<FournisseurM> findAllByDeletedOrderByCodeFournisseurNum(@Param("deleted") Boolean deletion, Pageable pageable);

    Page<FournisseurM> findByCodeFournisseurContainingIgnoreCaseAndDeleted(
            String codeFournisseur, Boolean deleted, Pageable pageable);

    Optional<FournisseurM> findFirstByIntituleFournisseurAndDeleted(String intituleFournisseur, Boolean deleted);

    FournisseurM findByCodeFournisseurAndDeleted(String codeFournisseur, Boolean deleted);
    FournisseurM findByIntituleFournisseurAndDeleted(String intituleFournisseur, Boolean deleted);

    // ✅ Nouvelle méthode combinée
    @Query("SELECT f FROM FournisseurM f WHERE f.deleted = :deleted AND (LOWER(f.codeFournisseur) = LOWER(:value) OR LOWER(f.intituleFournisseur) = LOWER(:value))")
    FournisseurM findByCodeOrIntitule(@Param("value") String value, @Param("deleted") Boolean deleted);


}
