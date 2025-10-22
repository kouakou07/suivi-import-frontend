package com.example.suivie_importBackend.repository;

import com.example.suivie_importBackend.models.CommandeM;
import com.example.suivie_importBackend.models.LigneCommande;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LigneCommandeRepository extends JpaRepository<LigneCommande, Long> {

    Optional<LigneCommande> findFirstByIdAndDeleted(Long id, Boolean deleted);

    List<LigneCommande> findByCommandeIdAndDeleted(Long commandeId, Boolean deleted);

    Page<LigneCommande> findAllByDeleted(Boolean deleted, Pageable page);
}
