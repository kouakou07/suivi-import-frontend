package com.example.suivie_importBackend.service;

import java.io.File;
import java.util.Base64;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ServeFileService {
    
//    @Value("${media.checks}")
//    private  String ROOT_DIRECTORY;
//
//    @Autowired
//    private ApplicationContext context;
//
//    @Autowired
//    RemiseRepository remiseRepository;
//
//    @Autowired
//    ChequeRepository chequeRepository;
//
//    @Autowired
//    UserService userService;
//
//    @Autowired
//    RoleService roleService;
//
//    @Autowired
//    AgenceUserRepository agenceUserRepository;
//
//    public Object getFile(String uri) throws Exception{
//
//        if(!uri.startsWith("C") && !uri.startsWith("R") && uri.length() < 3 ){
//            return ResponseEntity.notFound().build();
//        }
//        String absolutePath = "";
//        String type = uri.substring(uri.length() - 1);
//        String reliableUri = uri.substring(0, uri.length() - 1);
//
//        if(uri.startsWith("R")){
//            Optional<RemiseM> remise = this.remiseRepository.findFirstByUriAndDeleted(reliableUri, Deletion.NO);
//
//            if(remise.isEmpty()){
//                return ResponseEntity.notFound().build();
//            }
//
//            UserM user = userService.getAuthUser();
//            // on verifie si l'utilisateur appartient a l'agence, si oui on verifie a le droit de
//            // les images
//            if(roleService.isAgenceUser(user.getRole().getId())){
//                Boolean hasRight = agenceUserRepository.existsByAgenceAndUserAndDeleted(remise.get().getAgence(), user, Deletion.NO);
//                if(!hasRight){
//                    return ResponseEntity.notFound().build();
//                }
//            }
//
//            if(type.equals("D")){
//                absolutePath = remise.get().getPathData();
//            }else if(type.equals("R")){
//                absolutePath = remise.get().getPathImgR();
//            }else{
//                absolutePath = remise.get().getPathImgV();
//            }
//        }else{
//            Optional<ChequeM> cheque = this.chequeRepository.findFirstByUriAndDeleted(reliableUri, Deletion.NO);
//            if(cheque.isEmpty()){
//                return ResponseEntity.notFound().build();
//            }
//
//            UserM user = userService.getAuthUser();
//            // on verifie si l'utilisateur appartient a l'agence, si oui on verifie a le droit de
//            // les images
//            if(roleService.isAgenceUser(user.getRole().getId())){
//                Boolean hasRight = agenceUserRepository.existsByAgenceAndUserAndDeleted(cheque.get().getRemise().getAgence(), user, Deletion.NO);
//                if(!hasRight){
//                    return ResponseEntity.notFound().build();
//                }
//            }
//            if(type.equals("D")){
//                absolutePath = cheque.get().getPathData();
//            }else if(type.equals("R")){
//                absolutePath = cheque.get().getPathImgR();
//            }else{
//                absolutePath = cheque.get().getPathImgV();
//            }
//        }
//
//        Resource file = context.getResource("file:" + absolutePath);
//        if(!file.exists()){
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
//                .body("data:image;base64, " + Base64.getEncoder().encodeToString(file.getContentAsByteArray()));
//    }
}
