package com.example.suivie_importBackend.vo;

import com.example.suivie_importBackend.models.LigneCommande;

public record LigneCommandeVO(
        Long id,
        String reference,
        String designation,
        String uniteGestion,
        Double quantite,
        Double prixAchat,
        Double prixUnitaire,
        Double totalAchat,
        Double maf,
        Double fret,
        Double totalCfrDevise,
        Double tauxChange,
        Double totalCfrCfa,
        Double ddTaxeHt,
        Double faTransitHt,
        Double totalTransit,
        Double coutRevient,
        Double coeffCoutRevient,
        Double coutUnitaireHt,
        Double pvHtMarge40,
        Double pvHtMarge50,
        Double pvTtcMini,
        Double pvTtcMaxi,
        Double coeffMultiplicateur,
        String devise,
        String transitaire,
        String numeroBE,
        String declarationDouane
) {
    public static LigneCommandeVO fromEntity(LigneCommande ligne) {
        return new LigneCommandeVO(
                ligne.getId(),
                ligne.getReference(),
                ligne.getDesignation(),
                ligne.getUniteGestion(),
                ligne.getQuantite(),
                ligne.getPrixAchat(),
                ligne.getPrixUnitaire(),
                ligne.getTotalAchat(),
                ligne.getMaf(),
                ligne.getFret(),
                ligne.getTotalCfrDevise(),
                ligne.getTauxChange(),
                ligne.getTotalCfrCfa(),
                ligne.getDdTaxeHt(),
                ligne.getFaTransitHt(),
                ligne.getTotalTransit(),
                ligne.getCoutRevient(),
                ligne.getCoeffCoutRevient(),
                ligne.getCoutUnitaireHt(),
                ligne.getPvHtMarge40(),
                ligne.getPvHtMarge50(),
                ligne.getPvTtcMini(),
                ligne.getPvTtcMaxi(),
                ligne.getCoeffMultiplicateur(),
                ligne.getDevise(),
                ligne.getTransitaire(),
                ligne.getNumeroBE(),
                ligne.getDeclarationDouane()
        );
    }
}

