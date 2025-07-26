package com.example.suivie_importBackend.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.example.suivie_importBackend.Enum.Deletion;
import com.example.suivie_importBackend.models.MotifM;
import com.example.suivie_importBackend.repository.MotifRepository;
import com.example.suivie_importBackend.tools.ReportError;
import com.example.suivie_importBackend.validator.MotifValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import jakarta.validation.Valid;

@Service
public class MotifService {
    
    @Autowired
    MotifRepository motifRepository;

    @Autowired
    UserService userService;

    public Object listMotifAll(){
        List<MotifM> motifs = motifRepository.findByDeleted(Deletion.NO);
        return ResponseEntity.ok(motifs);
    }

    public Object listMotif(Integer page){
        Page<MotifM> motifs = motifRepository.findByDeleted(Deletion.NO, PageRequest.of(page, 25));
        return ResponseEntity.ok(motifs);
    }

    public Object infoMotif(Long id){
        Optional<MotifM> motifOpt = motifRepository.findFirstByIdAndDeleted(id, Deletion.NO);
        if(motifOpt.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(motifOpt.get());
    }

    public Object addMotif(@Valid MotifValidator form, BindingResult res){
        if(res.hasErrors()){
            return ResponseEntity.badRequest().body(ReportError.getErrors(res));
        }
        Boolean motifExistence = motifRepository.existsByCodeAndDeleted(form.getCode(), Deletion.NO);
        if(motifExistence){
            Map<String, String> errors = new HashMap<String, String>();
            errors.put("code", "Le code motif existe deja");
            return ResponseEntity.badRequest().body(errors);
        }
        MotifM motif = new MotifM(form.getCode(), form.getLibelle());
        motif = motifRepository.save(motif);
        userService.auditImportant("Ajout de motif", motif.toString());
        return ResponseEntity.ok(motif);
    }

    public Object editMotif(Long id, @Valid MotifValidator form, BindingResult res){
        Optional<MotifM> motifOpt = motifRepository.findFirstByIdAndDeleted(id, Deletion.NO);
        if(motifOpt.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        if(res.hasErrors()){
            return ResponseEntity.badRequest().body(ReportError.getErrors(res));
        }
        Boolean motifExistence = motifRepository.existsByCodeAndIdNotAndDeleted(form.getCode(), id, Deletion.NO);
        if(motifExistence){
            Map<String, String> errors = new HashMap<String, String>();
            errors.put("code", "Le code motif existe deja");
            return ResponseEntity.badRequest().body(errors);
        }
        MotifM motif = motifOpt.get();
        motif.update(form.getCode(), form.getLibelle());
        motif = motifRepository.save(motif);
        userService.auditImportant("Edition du motif", motif.toString());
        return ResponseEntity.ok(motif);
    }


    public Object remove(Long id){
        Optional<MotifM> motifOpt = motifRepository.findFirstByIdAndDeleted(id, Deletion.NO);
        if(motifOpt.isPresent()){
            MotifM motif = motifOpt.get();
            motif.setDeleted(Deletion.YES);
            motif = motifRepository.save(motif);
            userService.auditDanger("Motif supprime", motif.toString());
        }
        return ResponseEntity.ok("Le motif a ete supprime");
    }
}
