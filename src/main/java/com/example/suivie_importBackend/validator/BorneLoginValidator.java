package com.example.suivie_importBackend.validator;

import jakarta.validation.constraints.NotBlank;

public class BorneLoginValidator {
    @NotBlank
    private String borne;
    @NotBlank
    private String password;
    
    public String getBorne() {
        return borne;
    }
    public void setBorne(String borne) {
        this.borne = borne.trim();
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    
}
