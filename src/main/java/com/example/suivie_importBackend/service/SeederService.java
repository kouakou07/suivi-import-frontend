package com.example.suivie_importBackend.service;

import com.example.suivie_importBackend.models.UserM;
import com.example.suivie_importBackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SeederService {
    
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleService roleService;

    public void seed(){
        roleService.seeders();
        this.user();
        
    }

    public void user(){        
        UserM user = new UserM("ADMIN", null, null, "admin", "admin", roleService.getAdminBank());
        user.setPassword("123456789");
        this.userRepository.save(user);
    }
}
