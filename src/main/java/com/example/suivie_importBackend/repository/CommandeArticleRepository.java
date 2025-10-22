package com.example.suivie_importBackend.repository;

import com.example.suivie_importBackend.models.CommandeArticle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandeArticleRepository extends JpaRepository<CommandeArticle, Long> {
}
