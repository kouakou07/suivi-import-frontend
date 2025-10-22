package com.example.suivie_importBackend.repository;

import com.example.suivie_importBackend.models.Devise;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface DeviseRepository extends JpaRepository<Devise,Long>  {

    Optional<Devise> findFirstByIdAndDeleted(Long id, Boolean deletion);

    Page<Devise> findAllByDeleted(Boolean deleted, Pageable pageable);
}
