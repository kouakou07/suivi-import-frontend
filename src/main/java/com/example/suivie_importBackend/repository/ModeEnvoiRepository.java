package com.example.suivie_importBackend.repository;

import com.example.suivie_importBackend.models.ModeEnvoi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ModeEnvoiRepository extends JpaRepository<ModeEnvoi, Long>  {

    Optional<ModeEnvoi> findFirstByIdAndDeleted(Long id, Boolean deletion);

    Page<ModeEnvoi> findAllByDeleted(Boolean deleted, Pageable pageable);
}
