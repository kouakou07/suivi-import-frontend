package com.example.suivie_importBackend.vo;

import com.example.suivie_importBackend.models.FournisseurM;

public record FournisseurVO(
        String codeFournisseur,
        String intituleFournisseur
) {
    public static FournisseurVO fromEntity(FournisseurM fournisseur) {
        if (fournisseur == null) return null; // 🔹 protection contre null
        return new FournisseurVO(
                fournisseur.getCodeFournisseur(),
                fournisseur.getIntituleFournisseur()
        );
    }
}

