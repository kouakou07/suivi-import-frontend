package com.example.suivie_importBackend.repository;

import com.example.suivie_importBackend.models.FournisseurCentraleM;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface FournisseurCentraleRepository extends JpaRepository<FournisseurCentraleM, Long>  {

    List<FournisseurCentraleM> findByIdFournisseurCentraleAndDeleted(Long idFournisseurCentrale, Boolean deletion);

    Optional<FournisseurCentraleM> findByIdAndDeleted(Long idFournisseurCentrale, Boolean deletion);

    Optional<FournisseurCentraleM> findByIdFournisseurAndDeleted(
            Long idFournisseur,
            Boolean deletion);

}
