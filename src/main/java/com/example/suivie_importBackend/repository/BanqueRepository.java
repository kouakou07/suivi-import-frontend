package com.example.suivie_importBackend.repository;

import com.example.suivie_importBackend.models.Banque;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BanqueRepository extends JpaRepository<Banque, Long> {

    List<Banque> findAllByDeleted(Boolean deletion);

    Page<Banque> findAllByDeleted(Boolean deletion, Pageable pageable);
}
