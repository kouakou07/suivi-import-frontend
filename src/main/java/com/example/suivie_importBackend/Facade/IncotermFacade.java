package com.example.suivie_importBackend.Facade;


import com.example.suivie_importBackend.dto.IncotermDto;
import com.example.suivie_importBackend.service.IncotermService;
import com.example.suivie_importBackend.vo.IncotermVO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncotermFacade {
    private final IncotermService incotermService;

    public IncotermFacade(IncotermService incotermService) {
        this.incotermService = incotermService;
    }

    public IncotermDto enregistrer(IncotermVO vo) {
        return incotermService.enregistrer(vo);
    }

    public List<IncotermDto> listerTous() {
        return incotermService.listerTous();
    }

    public Page<IncotermDto> listerTousAvecPagination(int page) {
        return incotermService.listerTousAvecPagination(page);
    }

    public IncotermDto modifier(Long id, IncotermVO vo) {
        return incotermService.modifier(id, vo);
    }

    public boolean supprimer(Long id) {
        return incotermService.supprimer(id);
    }
}
