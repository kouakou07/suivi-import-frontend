package com.example.suivie_importBackend.validator;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class PasswordForm {
    @NotEmpty
    @NotBlank
    @Size(min = 6, max = 20)
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
