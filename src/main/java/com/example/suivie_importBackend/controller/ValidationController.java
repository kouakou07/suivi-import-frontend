package com.example.suivie_importBackend.controller;

import com.example.suivie_importBackend.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/validation")
public class ValidationController {

    @Autowired
    ValidationService  validationService;

}
