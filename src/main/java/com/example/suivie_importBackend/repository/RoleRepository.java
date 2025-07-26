package com.example.suivie_importBackend.repository;

import java.util.List;
import com.example.suivie_importBackend.models.RoleM;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleM, Long> {
    RoleM findByIdAndDeleted(Long id, Boolean deletion);
    RoleM findFirstByLibelleAndDeleted(String libelle, Boolean deletion);
    List<RoleM> findByLibelleInAndDeletedOrderByLibelleAsc(List<String> libelle, Boolean deletion);
}
