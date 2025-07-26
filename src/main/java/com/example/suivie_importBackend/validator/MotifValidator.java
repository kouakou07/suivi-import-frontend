package com.example.suivie_importBackend.validator;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public class MotifValidator {
    
    @NotEmpty
    @NotBlank
    private String code;

    @NotEmpty
    @NotBlank
    private String libelle;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    
}
