package com.example.suivie_importBackend.repository;

import java.util.List;
import java.util.Optional;

import com.example.suivie_importBackend.models.RoleM;
import com.example.suivie_importBackend.models.UserM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserM, Long>{
    Optional<UserM> findFirstByUsernameAndStatusAndDeleted(String username, Integer status, Boolean deletion);
    Optional<UserM> findFirstByUsernameAndStatusAndOldAndDeleted(String username, Integer status, Boolean statePassword, Boolean deletion);
    Optional<UserM> findFirstByUsernameAndDeleted(String username, Boolean deletion);
    Boolean existsByUsernameAndDeleted(String username, Boolean deletion);
    Page<UserM> findByIdNotAndRoleInAndDeletedOrderByNomAsc(Long id, List<RoleM> roles, Boolean deletion, Pageable page);
    Optional<UserM> findFirstByIdAndDeleted(Long id, Boolean deletion);
    Optional<UserM> findFirstByIdAndRoleInAndDeleted(Long id, List<RoleM> roles, Boolean deletion);
    Optional<UserM> findFirstByUsernameAndRoleInAndDeleted(String username, List<RoleM> roles, Boolean deletion);
}
