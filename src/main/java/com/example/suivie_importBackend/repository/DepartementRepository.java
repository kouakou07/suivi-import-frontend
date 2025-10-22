package com.example.suivie_importBackend.repository;

import com.example.suivie_importBackend.models.Departement;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface DepartementRepository extends JpaRepository<Departement, Long> {

    Optional<Departement> findFirstByIdAndDeleted(Long id, Boolean deletion);

    List<Departement> findByDeleted(Boolean deletion);
}
