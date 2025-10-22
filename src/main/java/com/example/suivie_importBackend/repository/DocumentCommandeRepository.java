package com.example.suivie_importBackend.repository;

import com.example.suivie_importBackend.models.DocumentCommande;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentCommandeRepository extends JpaRepository<DocumentCommande, Long> {
}
