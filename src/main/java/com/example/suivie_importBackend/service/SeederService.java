package com.example.suivie_importBackend.service;

import com.example.suivie_importBackend.models.UserM;
import com.example.suivie_importBackend.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SeederService {

    private final UserRepository userRepository;
    private final RoleService roleService;

    public SeederService(UserRepository userRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    @Transactional
    public void seed() {
        System.out.println("Début de la création des rôles...");
        roleService.seeders();
        System.out.println("Début de la création de l'utilisateur admin...");
        this.createAdminUser();
    }

    public void createAdminUser() {
        UserM user = new UserM("ADMIN", null, null, "admin", "admin", roleService.getAdminBank());
        user.setPassword("123456789");
        System.out.println("Mot de passe haché pour '123456789' : " + user.getPassword());
        this.userRepository.save(user);
    }

    public long countUsers() {
        return userRepository.count();
    }
}

