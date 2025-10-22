package com.example.suivie_importBackend.repository;

import com.example.suivie_importBackend.models.Departement;
import com.example.suivie_importBackend.models.Paiement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PaiementRepository extends JpaRepository<Paiement, Long> {


    Optional<Departement> findFirstByIdAndDeleted(Long id, Boolean deletion);

    List<Departement> findByDeleted(Boolean deletion);

}
