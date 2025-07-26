package com.example.suivie_importBackend.controller;

import com.example.suivie_importBackend.repository.UserRepository;
import com.example.suivie_importBackend.service.JwtService;
import com.example.suivie_importBackend.service.RoleService;
import com.example.suivie_importBackend.service.UserService;
import com.example.suivie_importBackend.validator.LoginForm;
import com.example.suivie_importBackend.validator.PasswordForm;
import com.example.suivie_importBackend.validator.UserBankForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class UserController {
    
    @Autowired
    JwtService jwtService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public Object login(@Valid LoginForm form, BindingResult res){
        return userService.login(form, res);
    }
    
    @Secured("ROLE_USER")
    @GetMapping("/profil")
    public Object profile(){
        return userService.myProfile();
    }
    
    @Secured("ROLE_ADMIN")
    @PostMapping("/user/change-mdp")
    public Object changePassword(@Valid PasswordForm form, BindingResult res){
        return userService.changePassword(form, res);
    }


    @PostMapping("/login/password")
    public Object changePasswordAtLogin(@Valid PasswordForm form, BindingResult res){
        return userService.changePasswordAtLogin(form, res);
    }



    @Secured("ROLE_ADMIN")
    @GetMapping("/liste/{page}/user/banque")
    public Object listUsersBanque(@PathVariable Integer page){
        return userService.listBankUsers(page);
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/fetch/create/user/banque")
    public Object fetchRoleEcobankUser(){
        return ResponseEntity.ok(roleService.bankUser());
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/fetch/create/user/agence")
    public Object fetchRoleAgenceUser(){
        return ResponseEntity.ok(roleService.agenceUser());
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/create/user/banque")
    public Object createUserBank(@Valid UserBankForm form, BindingResult res){
        return userService.createBankUser(form, res);
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/fetch/info/{userId}/user/banque")
    public Object infoUserBank(@PathVariable Long userId){
        return userService.infoUserBank(userId);
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/edition/user/banque")
    public Object editionUserBank(@Valid UserBankForm form, BindingResult res){
        return userService.editBankUser(form, res);
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/user/{userId}/reinitialise-mdp")
    public Object resetPassword(@PathVariable Long userId){
        return userService.resetPassword(userId);
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/user/{userId}/changer-status")
    public Object changeStatus(@PathVariable Long userId){
        return userService.changeStatus(userId);
    }
    

}
