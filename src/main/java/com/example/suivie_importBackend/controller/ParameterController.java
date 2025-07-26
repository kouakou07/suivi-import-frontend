package com.example.suivie_importBackend.controller;

import com.example.suivie_importBackend.service.ParameterService;
import com.example.suivie_importBackend.validator.ParameterValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class ParameterController {
    
    @Autowired
    ParameterService parameterService;

    @Secured("ROLE_IT")
    @GetMapping("/parametre/tentative/connection")
    public Object getAll(){
        return ResponseEntity.ok(parameterService.getAttemptConnection());
    }


    @Secured("ROLE_IT")
    @GetMapping("/parametre/session")
    public Object getAllSession(){
        return parameterService.getAllSessions();
    }

    @Secured("ROLE_IT")
    @PostMapping("/parametre/update")
    public Object update(@Valid ParameterValidator form, BindingResult res){
        return parameterService.update(form, res);
    }

    @Secured("ROLE_IT")
    @GetMapping("/parametre/integration")
    public Object integrationInfo(){
        return ResponseEntity.ok(parameterService.getRemoteIntegrationDir());
    }



}
