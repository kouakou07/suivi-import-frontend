package com.example.suivie_importBackend.repository;

import com.example.suivie_importBackend.models.CommandeM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommandeRepository extends JpaRepository<CommandeM, Long> {

    Optional<CommandeM> findByIdAndDeleted(Long id, Boolean deleted);

    List<CommandeM> findAllByDeleted(Boolean deleted);

    Page<CommandeM> findAllByDeleted(Boolean deleted, Pageable pageable);

    boolean existsByNumeroCommandeAndDeleted(String numeroCommande, Boolean deleted);
}
