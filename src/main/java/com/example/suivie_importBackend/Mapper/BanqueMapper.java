package com.example.suivie_importBackend.Mapper;

import com.example.suivie_importBackend.dto.BanqueDto;
import com.example.suivie_importBackend.models.Banque;
import com.example.suivie_importBackend.vo.BanqueVO;

public class BanqueMapper {

    public static Banque versEntite(BanqueVO vo) {
        if (vo == null) return null;
        return Banque.builder()
                .libelle(vo.getLibelle())
                .numeroCompte(vo.getNumeroCompte())
                .build();
    }

    public static BanqueDto versDTO(Banque banque) {
        if (banque == null) return null;
        return BanqueDto.builder()
                .id(banque.getId())
                .libelle(banque.getLibelle())
                .numeroCompte(banque.getNumeroCompte())
                .build();
    }
}
