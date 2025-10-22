package com.example.suivie_importBackend.repository;

import com.example.suivie_importBackend.models.AcheteurM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface AcheteurRepository extends JpaRepository<AcheteurM, Long> {

    Page<AcheteurM> findAllByDeleted(Boolean deletion, Pageable page);

    List<AcheteurM> findAllByDeleted(Boolean deletion);

    Optional<AcheteurM> findFirstByIdAndDeleted(Long id, Boolean deletion);
}
