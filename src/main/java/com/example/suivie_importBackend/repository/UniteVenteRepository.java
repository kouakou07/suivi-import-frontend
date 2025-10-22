package com.example.suivie_importBackend.repository;

import com.example.suivie_importBackend.models.UniteVente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UniteVenteRepository extends JpaRepository<UniteVente, Long> {

    Optional<UniteVente> findFirstByIdAndDeleted(Long id, Boolean deletion);

    List<UniteVente> findAllByDeleted(Boolean deletion);

    Page<UniteVente> findAllByDeleted(Boolean deletion, Pageable pageable);
}
