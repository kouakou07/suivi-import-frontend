package com.example.suivie_importBackend.repository;

import com.example.suivie_importBackend.models.CommandeM;
import com.example.suivie_importBackend.models.Pays;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaysRepository extends JpaRepository<Pays, Long>  {

    Optional<Pays> findFirstByIdAndDeleted(Long id, Boolean deletion);

    Page<Pays> findAllByDeleted(Boolean deleted, Pageable pageable);

}
