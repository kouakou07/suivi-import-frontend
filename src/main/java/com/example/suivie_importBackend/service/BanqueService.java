package com.example.suivie_importBackend.service;

import com.example.suivie_importBackend.Enum.Deletion;
import com.example.suivie_importBackend.Mapper.BanqueMapper;
import com.example.suivie_importBackend.dto.BanqueDto;
import com.example.suivie_importBackend.models.Banque;
import com.example.suivie_importBackend.repository.BanqueRepository;
import com.example.suivie_importBackend.vo.BanqueVO;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.*;

import java.util.List;

@Service
public class BanqueService {


    private final BanqueRepository banqueRepository;

    public BanqueService(BanqueRepository banqueRepository) {
        this.banqueRepository = banqueRepository;
    }

    @Transactional
    public BanqueDto enregistrer(BanqueVO vo) {
        Banque entity = BanqueMapper.versEntite(vo);
        Banque saved = banqueRepository.save(entity);
        return BanqueMapper.versDTO(saved);
    }

    @Transactional(Transactional.TxType.SUPPORTS)
    public List<BanqueDto> listerTous() {
        return banqueRepository.findAllByDeleted(Deletion.NO)
                .stream()
                .map(BanqueMapper::versDTO)
                .toList();
    }

    @Transactional(Transactional.TxType.SUPPORTS)
    public Page<BanqueDto> listerTousAvecPagination(int page) {
      Page<Banque>  banques =  banqueRepository.findAllByDeleted(Deletion.NO, PageRequest.of(page, 10));
        return banques.map(BanqueMapper::versDTO);
    }

    @Transactional
    public BanqueDto modifier(Long id, BanqueVO vo) {
        return banqueRepository.findById(id)
                .map(existing -> {
                    existing.setLibelle(vo.getLibelle());
                    existing.setNumeroCompte(vo.getNumeroCompte());
                    Banque updated = banqueRepository.save(existing);
                    return BanqueMapper.versDTO(updated);
                })
                .orElseThrow(() -> new RuntimeException("Banque non trouvée"));
    }

    @Transactional
    public boolean supprimer(Long id) {
        if (banqueRepository.existsById(id)) {
            banqueRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
