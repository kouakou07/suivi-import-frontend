package com.example.suivie_importBackend.repository;

import java.util.List;
import java.util.Optional;

import com.example.suivie_importBackend.models.MotifM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MotifRepository extends JpaRepository<MotifM, Long> {
 
    Boolean existsByCodeAndDeleted(String code, Boolean deletion);
    Boolean existsByCodeAndIdNotAndDeleted(String code, Long id, Boolean deletion);
    Optional<MotifM> findFirstByIdAndDeleted(Long id, Boolean deletion);
    Page<MotifM> findByDeleted(Boolean deletion, Pageable page);
    List<MotifM> findByDeleted(Boolean deletion);
}
