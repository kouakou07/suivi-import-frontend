package com.example.suivie_importBackend.repository;

import com.example.suivie_importBackend.models.Incoterm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface IncotermRepository extends JpaRepository<Incoterm, Long> {

    List<Incoterm>findAllByDeleted(Boolean deletion);

    Page<Incoterm> findAllByDeleted(Boolean deletion, Pageable pageable);

    Optional<Incoterm> findByIdAndDeleted(Long id, Boolean deletion);
}
