package com.example.suivie_importBackend.vo;

import com.example.suivie_importBackend.models.UserM;

public record UserVO(
        Long id,
        String nom
) {
    public static UserVO fromEntity(UserM user) {
        return new UserVO(
                user.getId(),
                user.getNom()
        );
    }
}

