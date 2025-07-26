package com.example.suivie_importBackend.controller;

import com.example.suivie_importBackend.service.RapportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rapport")
public class RapportController {
    
//    @Autowired
//    RapportService rapportService;

//    @Secured("ROLE_USER")
//    @GetMapping("/data")
//    public Object getData(){
//        return rapportService.init();
//    }

//    @Secured("ROLE_USER")
//    @PostMapping("/get")
//    public Object rapport(@Valid RapportValidator form, BindingResult res){
//        return rapportService.process(form, res);
//    }
}
