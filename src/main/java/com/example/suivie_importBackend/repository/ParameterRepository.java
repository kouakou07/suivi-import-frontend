package com.example.suivie_importBackend.repository;

import java.util.List;
import java.util.Optional;
import com.example.suivie_importBackend.models.ParameterM;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParameterRepository extends JpaRepository<ParameterM, Long> {

    Optional<ParameterM> findFirstByCodeAndDeleted(String code, Boolean deletion);

    List<ParameterM> findByDeleted(Boolean deletion);
}
