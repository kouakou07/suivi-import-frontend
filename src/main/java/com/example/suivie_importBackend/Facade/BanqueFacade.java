package com.example.suivie_importBackend.Facade;

import com.example.suivie_importBackend.dto.BanqueDto;
import com.example.suivie_importBackend.service.BanqueService;
import com.example.suivie_importBackend.vo.BanqueVO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BanqueFacade {

    private final BanqueService banqueService;

    public BanqueFacade(BanqueService banqueService) {
        this.banqueService = banqueService;
    }

    public BanqueDto enregistrer(BanqueVO banqueVO) {
        return banqueService.enregistrer(banqueVO);
    }

    public List<BanqueDto> listerTous() {
        return banqueService.listerTous();
    }

    public Page<BanqueDto> listerTousAvecPagination(int page) {
        return banqueService.listerTousAvecPagination(page);
    }

    public BanqueDto modifier(Long id, BanqueVO vo) {
        return banqueService.modifier(id, vo);
    }

    public boolean supprimer(Long id) {
        return banqueService.supprimer(id);
    }
}
