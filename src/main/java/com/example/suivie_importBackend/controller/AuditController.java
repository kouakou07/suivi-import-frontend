package com.example.suivie_importBackend.controller;

import com.example.suivie_importBackend.service.AuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class AuditController {
    
    @Autowired
    AuditService auditService;

//    @Secured("ROLE_IT")
    @GetMapping("/audit/{page}")
    public Object list(@PathVariable Integer page){
        return auditService.allAudits(page);
    }
}
