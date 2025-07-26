package com.example.suivie_importBackend.validator;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserBankForm {
    @NotEmpty
    @Size(max = 20)
    private String username;
    @NotEmpty
    private String nom;
    @NotEmpty
    private String prenom;
    @NotEmpty
    @Email
    private String email;
    @NotNull
    @Min(value = 1)
    private Long role;

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username.trim();
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom.trim();
    }
    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom.trim();
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email.trim();
    }
    public Long getRole() {
        return role;
    }
    public void setRole(Long role) {
        this.role = role;
    }
}
