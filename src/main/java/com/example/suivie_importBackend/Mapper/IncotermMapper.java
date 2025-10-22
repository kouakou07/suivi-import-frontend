package com.example.suivie_importBackend.Mapper;

import com.example.suivie_importBackend.dto.IncotermDto;
import com.example.suivie_importBackend.models.Incoterm;
import com.example.suivie_importBackend.vo.IncotermVO;

public class IncotermMapper {

    public static Incoterm versEntite(IncotermVO vo) {
        return Incoterm.builder()
                .intercoterm(vo.getIncoterm())
                .signification(vo.getSignification())
                .modeTransport(vo.getModeTransport())
                .responsableVendeur(vo.getResponsableVendeur())
                .build();
    }

    public static IncotermDto versDTO(Incoterm incoterm) {
        return IncotermDto.builder()
                .id(incoterm.getId())
                .intercoterm(incoterm.getIntercoterm())
                .signification(incoterm.getSignification())
                .modeTransport(incoterm.getModeTransport())
                .responsableVendeur(incoterm.getResponsableVendeur())
                .build();
    }
}
