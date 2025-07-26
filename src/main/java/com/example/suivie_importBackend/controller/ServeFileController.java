package com.example.suivie_importBackend.controller;

import com.example.suivie_importBackend.service.ServeFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ServeFileController {
 
//    @Autowired
//    ServeFileService serveFileService;
//
//    @Secured("ROLE_USER")
//    @GetMapping("/static/{uri}")
//    public Object serve(@PathVariable String uri) throws Exception{
//        return serveFileService.getFile(uri);
//    }
}
