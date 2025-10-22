package com.example.suivie_importBackend.repository;

import com.example.suivie_importBackend.models.FamilleCentrale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface FamilleCentraleRepository extends JpaRepository<FamilleCentrale, Long> {

    Optional<FamilleCentrale> findFirstByIdAndDeleted(Long id, Boolean deletion);

    Page<FamilleCentrale> findAllByDeleted(Boolean deleted, Pageable pageable);
}
