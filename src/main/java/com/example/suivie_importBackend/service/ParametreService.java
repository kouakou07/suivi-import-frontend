package com.example.suivie_importBackend.service;

import com.example.suivie_importBackend.Enum.Deletion;
import com.example.suivie_importBackend.dto.*;
import com.example.suivie_importBackend.models.Devise;
import com.example.suivie_importBackend.models.FamilleCentrale;
import com.example.suivie_importBackend.models.ModeEnvoi;
import com.example.suivie_importBackend.models.Pays;
import com.example.suivie_importBackend.repository.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ParametreService {

    private final PaysRepository paysRepository;
    private final ModeEnvoiRepository modeEnvoiRepository;
    private final ModePaiementRepository modePaiementRepository;
    private final DepartementRepository departementRepository;
    private final DeviseRepository deviseRepository;
    private final FamilleCentraleRepository familleCentraleRepository;

    public ParametreService(PaysRepository paysRepository, ModeEnvoiRepository modeEnvoiRepository, ModePaiementRepository modePaiementRepository, DepartementRepository departementRepository, FamilleCentraleRepository familleCentraleRepository,DeviseRepository deviseRepository) {
        this.paysRepository = paysRepository;
        this.modeEnvoiRepository = modeEnvoiRepository;
        this.modePaiementRepository = modePaiementRepository;
        this.departementRepository = departementRepository;
        this.deviseRepository = deviseRepository;
        this.familleCentraleRepository = familleCentraleRepository;
    }

    @Transactional
    public ResponseEntity<Pays> creerPays(@RequestBody Pays pays) {
        Pays pays1 = new Pays();
        pays1.setDeleted(Deletion.NO);
        Pays enregistrePays = paysRepository.save(pays);
        return ResponseEntity.status(HttpStatus.CREATED).body(enregistrePays);
    }

    @Transactional
    public PaysDto mettreAJourPays(PaysDto paysDto) {
        Optional<Pays> optionalPays = paysRepository.findFirstByIdAndDeleted(paysDto.id(), Deletion.NO);
        if (optionalPays.isPresent()) {
            Pays pays = optionalPays.get();
            pays.setIntitule(paysDto.intitule());
            Pays paysMisAJour = paysRepository.save(pays);
            return new PaysDto(paysMisAJour.getId(), paysMisAJour.getIntitule());
        }
        throw new NoSuchElementException("Pays non trouvé");
    }

    @Transactional(readOnly = true)
    public Page<PaysDtoList> recupererPays(Pageable pageable) {
        return paysRepository.findAllByDeleted(Deletion.NO, pageable)
                .map(c -> PaysDtoList.builder()
                        .id(c.getId())
                        .intitule(c.getIntitule())
                        .build()
                );
    }

    public boolean supprimerPays(Long id) {
        Optional<Pays> optionalPays = paysRepository.findFirstByIdAndDeleted(id, Deletion.NO);
        if (optionalPays.isPresent()) {
            Pays recupererPay = optionalPays.get();
            recupererPay.setDeleted(Deletion.YES);
            paysRepository.save(recupererPay);
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    public ModeEnvoi creerModeEnvoi(@RequestBody ModeEnvoi modeEnvoi) {
        ModeEnvoi modeEnvoi1 = new ModeEnvoi();
        modeEnvoi1.setIntitule(modeEnvoi.getIntitule());
        modeEnvoi1.setDeleted(Deletion.NO);
        return modeEnvoiRepository.save(modeEnvoi1);
    }

    @Transactional
    public ModeEnvoiDto mettreAJourModeEnvoi(ModeEnvoiDto modeEnvoiDto) {
        Optional<ModeEnvoi> optionalModeEnvoi = modeEnvoiRepository.findFirstByIdAndDeleted(modeEnvoiDto.id(), Deletion.NO);
        if (optionalModeEnvoi.isPresent()) {
            ModeEnvoi mode = optionalModeEnvoi.get();
            mode.setIntitule(modeEnvoiDto.intitule());
            ModeEnvoi paysMisAJour = modeEnvoiRepository.save(mode);
            return new ModeEnvoiDto(paysMisAJour.getId(), paysMisAJour.getIntitule());
        }
        throw new NoSuchElementException("Pays non trouvé");
    }

    @Transactional(readOnly = true)
    public Page<ModeEnvoiListDto> recupererModeEnvoi(Pageable pageable) {
        return modeEnvoiRepository.findAllByDeleted(Deletion.NO, pageable)
                .map(c -> ModeEnvoiListDto.builder()
                        .id(c.getId())
                        .intitule(c.getIntitule())
                        .build()
                );
    }

    public boolean supprimerModeEnvoi(Long id) {
        Optional<ModeEnvoi> optionalModeEnvoi = modeEnvoiRepository.findFirstByIdAndDeleted(id, Deletion.NO);
        if (optionalModeEnvoi.isPresent()) {
            ModeEnvoi recupererModeEnvoi = optionalModeEnvoi.get();
            recupererModeEnvoi.setDeleted(Deletion.YES);
            modeEnvoiRepository.save(recupererModeEnvoi);
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    public Devise creerDevise(@RequestBody DeviseDto devise) {
        Devise devise1 = new Devise();
        devise1.setIntitule(devise.intitule());
        devise1.setDeleted(Deletion.NO);
       return deviseRepository.save(devise1);
    }

    @Transactional
    public DeviseDto mettreAJourDevise(DeviseDto deviseDto) {
        Optional<Devise> optionalDevise = deviseRepository.findFirstByIdAndDeleted(deviseDto.id(), Deletion.NO);
        if (optionalDevise.isPresent()) {
            Devise devise = optionalDevise.get();
            devise.setIntitule(deviseDto.intitule());
            Devise deviseMisAJour = deviseRepository.save(devise);
            return new DeviseDto(deviseMisAJour.getId(), deviseMisAJour.getIntitule());
        }
        throw new NoSuchElementException("Devise non trouvé");
    }

    @Transactional(readOnly = true)
    public Page<DeviseDtoList> recupererDevise(int page) {
        return deviseRepository.findAllByDeleted(Deletion.NO, PageRequest.of(page, 10))
                .map(c -> DeviseDtoList.builder()
                        .id(c.getId())
                        .intitule(c.getIntitule())
                        .build()
                );
    }

    public boolean supprimerDevise(Long id) {
        Optional<Devise> optionalDevise = deviseRepository.findFirstByIdAndDeleted(id, Deletion.NO);
        if (optionalDevise.isPresent()) {
            Devise recupererDevise = optionalDevise.get();
            recupererDevise.setDeleted(Deletion.YES);
            deviseRepository.save(recupererDevise);
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    public FamilleCentrale creerFamilleCentrale(@RequestBody FamilleCentrale familleCentrale) {
        FamilleCentrale familleCentrale1 = new FamilleCentrale();
        familleCentrale1.setIntitule(familleCentrale.getIntitule());
        familleCentrale1.setDeleted(Deletion.NO);
    return familleCentraleRepository.save(familleCentrale1);

    }

    @Transactional
    public FamilleCentraleDto mettreAJourFamilleCentrale(FamilleCentraleDto familleCentraleDto) {
        Optional<FamilleCentrale> optionalFamilleCentrale = familleCentraleRepository.findFirstByIdAndDeleted(familleCentraleDto.id(), Deletion.NO);
        if (optionalFamilleCentrale.isPresent()) {
            FamilleCentrale familleCentrale = optionalFamilleCentrale.get();
            familleCentrale.setIntitule(familleCentraleDto.intitule());
            FamilleCentrale familleCentraleMisAJour = familleCentraleRepository.save(familleCentrale);
            return new FamilleCentraleDto(familleCentraleMisAJour.getId(), familleCentraleMisAJour.getIntitule());
        }
        throw new NoSuchElementException("Devise non trouvé");
    }

    @Transactional(readOnly = true)
    public Page<FamilleCentraleDtoList> recupererFamilleCentrale(Pageable pageable) {
        return familleCentraleRepository.findAllByDeleted(Deletion.NO, pageable)
                .map(c -> FamilleCentraleDtoList.builder()
                        .id(c.getId())
                        .intitule(c.getIntitule())
                        .build()
                );
    }

    public boolean supprimerFamilleCentrale(Long id) {
        Optional<FamilleCentrale> optionalFamilleCentrale = familleCentraleRepository.findFirstByIdAndDeleted(id, Deletion.NO);
        if (optionalFamilleCentrale.isPresent()) {
            FamilleCentrale familleCentrale = optionalFamilleCentrale.get();
            familleCentrale.setDeleted(Deletion.YES);
            familleCentraleRepository.save(familleCentrale);
            return true;
        } else {
            return false;
        }
    }
}
