package com.example.suivie_importBackend.repository;

import com.example.suivie_importBackend.models.ModePaiement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ModePaiementRepository extends JpaRepository<ModePaiement,Long> {

    Optional<ModePaiement> findFirstByIdAndDeleted(Long id, Boolean deletion);

    List<ModePaiement> findByDeleted(Boolean deletion);
}
