package com.example.suivie_importBackend.vo;

import com.example.suivie_importBackend.Enum.StatusCommande;
import com.example.suivie_importBackend.models.CommandeM;
import com.example.suivie_importBackend.models.Incoterm;
import java.time.LocalDate;
import java.util.List;

public record CommandeVO(
        Long id,
        String numeroCommande,
        LocalDate dateProforma,
        Incoterm incoterm,
        String devise,
        String modeEnvoi,
        String clientDestinataire,
        StatusCommande statut,
//        FournisseurVO fournisseur,
        List<LigneCommandeVO> lignes
) {
    public static CommandeVO fromEntity(CommandeM commande) {
        return new CommandeVO(
                commande.getId(),
                commande.getNumeroCommande(),
                commande.getDateProforma(),
                commande.getIncoterm(),
                commande.getDevise(),
                commande.getModeEnvoi(),
                commande.getClientDestinataire(),
                commande.getStatut(),
                // 🔹 Sécurisation contre null
//                FournisseurVO.fromEntity(commande.getFournisseur()),
                commande.getLignes().stream()
                        .map(LigneCommandeVO::fromEntity)
                        .toList()
        );
    }
}

