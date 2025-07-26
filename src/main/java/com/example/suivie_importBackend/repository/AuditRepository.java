package com.example.suivie_importBackend.repository;

import com.example.suivie_importBackend.models.AuditM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditRepository extends JpaRepository<AuditM, Long> {
    
    Page<AuditM> findByDeletedOrderByDateCreationDesc(Boolean deletion, Pageable page);
}
