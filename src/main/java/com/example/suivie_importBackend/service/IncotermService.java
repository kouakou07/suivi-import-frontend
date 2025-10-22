package com.example.suivie_importBackend.service;

import com.example.suivie_importBackend.Enum.Deletion;
import com.example.suivie_importBackend.Mapper.IncotermMapper;
import com.example.suivie_importBackend.dto.IncotermDto;
import com.example.suivie_importBackend.models.Incoterm;
import com.example.suivie_importBackend.repository.IncotermRepository;
import com.example.suivie_importBackend.vo.IncotermVO;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class IncotermService {
    private final IncotermRepository incotermRepository;

    public IncotermService(IncotermRepository incotermRepository) {
        this.incotermRepository = incotermRepository;
    }

    @Transactional
    public IncotermDto enregistrer(IncotermVO vo) {
        Incoterm entity = IncotermMapper.versEntite(vo);
        Incoterm saved = incotermRepository.save(entity);
        return IncotermMapper.versDTO(saved);
    }

    @Transactional(Transactional.TxType.SUPPORTS)
    public List<IncotermDto> listerTous() {
        return incotermRepository.findAllByDeleted(Deletion.NO)
                .stream()
                .map(IncotermMapper::versDTO)
                .toList();
    }

    @Transactional(Transactional.TxType.SUPPORTS)
    public Page<IncotermDto> listerTousAvecPagination(int page) {
        Page<Incoterm>  incoterms = incotermRepository.findAllByDeleted(Deletion.NO, PageRequest.of(page, 10));
              return  incoterms.map(IncotermMapper::versDTO);
    }

    @Transactional
    public IncotermDto modifier(Long id, IncotermVO vo) {
        return incotermRepository.findById(id)
                .map(existing -> {
                    existing.setIntercoterm(vo.getIncoterm());
                    existing.setSignification(vo.getSignification());
                    existing.setModeTransport(vo.getModeTransport());
                    existing.setResponsableVendeur(vo.getResponsableVendeur());
                    Incoterm updated = incotermRepository.save(existing);
                    return IncotermMapper.versDTO(updated);
                })
                .orElseThrow(() -> new RuntimeException("Incoterm non trouvé"));
    }

    @Transactional
    public boolean supprimer(Long id) {
        if (incotermRepository.existsById(id)) {
            incotermRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
