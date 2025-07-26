package com.example.suivie_importBackend.controller;

import com.example.suivie_importBackend.service.MotifService;
import com.example.suivie_importBackend.validator.MotifValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/motif")
public class MotifController {

    @Autowired
    MotifService motifService;
    
    @Secured({"ROLE_IT", "ROLE_VALIDATEUR", "ROLE_INTEGRATEUR", "ROLE_SUPERVISEUR"})
    @GetMapping("/liste")
    public Object listAll(){
        return motifService.listMotifAll();
    }
    @Secured("ROLE_IT")
    @GetMapping("/liste/{page}")
    public Object list(@PathVariable Integer page){
        return motifService.listMotif(page);
    }

    @Secured("ROLE_IT")
    @GetMapping("/info/{id}")
    public Object info(@PathVariable Long id){
        return motifService.infoMotif(id);
    }

    @Secured("ROLE_IT")
    @PostMapping("/ajouter")
    public Object add(@Valid MotifValidator form, BindingResult res){
        return motifService.addMotif(form, res);
    }

    @Secured("ROLE_IT")
    @PostMapping("/editer/{id}")
    public Object editer(@PathVariable Long id, @Valid MotifValidator form, BindingResult res){
        return motifService.editMotif(id, form, res);
    }

    @Secured("ROLE_IT")
    @PostMapping("/supprimer/{id}")
    public Object remove(@PathVariable Long id){
        return motifService.remove(id);
    }
}
