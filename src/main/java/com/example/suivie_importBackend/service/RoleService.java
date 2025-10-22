package com.example.suivie_importBackend.service;

import java.util.ArrayList;
import java.util.List;

import com.example.suivie_importBackend.Enum.Deletion;
import com.example.suivie_importBackend.models.RoleM;
import com.example.suivie_importBackend.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public final String ROLE_ADMIN_BANK = "Admin";
    public final String ROLE_IT_BANK = "IT";
    public final String ROLE_VALIDATEUR_BANK = "Logisticien";
    public final String ROLE_CORRECTION = "Comptable";
    public final String ROLE_SUPER_VALIDATEUR_BANK = "Integrateur";
    public final String ROLE_SUPERVISOR_BANK = "Superviseur";
    public final String ROLE_ACHETEUR = "Acheteur";

    public void seeders(){
        this.roleRepository.save(new RoleM(ROLE_ADMIN_BANK));
        this.roleRepository.save(new RoleM(ROLE_IT_BANK));
        this.roleRepository.save(new RoleM(ROLE_VALIDATEUR_BANK));
        this.roleRepository.save(new RoleM(ROLE_CORRECTION));
        this.roleRepository.save(new RoleM(ROLE_SUPER_VALIDATEUR_BANK));
        this.roleRepository.save(new RoleM(ROLE_SUPERVISOR_BANK));
        this.roleRepository.save(new RoleM(ROLE_ACHETEUR));
        
    }

    public RoleM getAdminBank(){
        return this.roleRepository.findFirstByLibelleAndDeleted(ROLE_ADMIN_BANK, Deletion.NO);
    }
    public RoleM getItBank(){
        return this.roleRepository.findFirstByLibelleAndDeleted(ROLE_IT_BANK, Deletion.NO);
    }
    public RoleM getValidateurBank(){
        return this.roleRepository.findFirstByLibelleAndDeleted(ROLE_VALIDATEUR_BANK, Deletion.NO);
    }
    public RoleM getCorrecteurBank(){
        return this.roleRepository.findFirstByLibelleAndDeleted(ROLE_CORRECTION, Deletion.NO);
    }
    public RoleM getSuperValidateurBank(){
        return this.roleRepository.findFirstByLibelleAndDeleted(ROLE_SUPER_VALIDATEUR_BANK, Deletion.NO);
    }

    public RoleM getSuperviseurBank(){
        return this.roleRepository.findFirstByLibelleAndDeleted(ROLE_SUPERVISOR_BANK, Deletion.NO);
    }

    public RoleM getAcheteur(){
        return this.roleRepository.findFirstByLibelleAndDeleted(ROLE_ACHETEUR, Deletion.NO);
    }
   

    public Boolean isBankUser(Long id){
        RoleM role = roleRepository.findByIdAndDeleted(id, Deletion.NO);
        if(role != null){
            return role.getLibelle().equals(ROLE_ADMIN_BANK) ||
                    role.getLibelle().equals(ROLE_IT_BANK)  ||
                    role.getLibelle().equals(ROLE_VALIDATEUR_BANK) ||
                    role.getLibelle().equals(ROLE_SUPER_VALIDATEUR_BANK) ||
                    role.getLibelle().equals(ROLE_SUPERVISOR_BANK) ||
                    role.getLibelle().equals(ROLE_ACHETEUR);

        }
        return false;
    }

    public Boolean isAgenceUser(Long id){
        RoleM role = roleRepository.findByIdAndDeleted(id, Deletion.NO);
        if(role != null){
            return role.getLibelle().equals(ROLE_CORRECTION);
        }
        return false;
    }

    

    public List<RoleM> globalUser(){
        List<String> libelles = new ArrayList<String>();
        libelles.add(ROLE_ADMIN_BANK);
        libelles.add(ROLE_IT_BANK);
        libelles.add(ROLE_VALIDATEUR_BANK);
        libelles.add(ROLE_CORRECTION);
        libelles.add(ROLE_SUPER_VALIDATEUR_BANK);
        libelles.add(ROLE_SUPERVISOR_BANK);
        libelles.add(ROLE_ACHETEUR);
        return roleRepository.findByLibelleInAndDeletedOrderByLibelleAsc(libelles, Deletion.NO);
    }

    public List<RoleM> bankUser(){
        List<String> libelles = new ArrayList<String>();
        libelles.add(ROLE_ADMIN_BANK);
        libelles.add(ROLE_IT_BANK);
        libelles.add(ROLE_VALIDATEUR_BANK);
        libelles.add(ROLE_SUPER_VALIDATEUR_BANK);
        libelles.add(ROLE_SUPERVISOR_BANK);
        libelles.add(ROLE_ACHETEUR);
        return roleRepository.findByLibelleInAndDeletedOrderByLibelleAsc(libelles, Deletion.NO);
    }

    public List<RoleM> agenceUser(){
        List<String> libelles = new ArrayList<String>();
        libelles.add(ROLE_CORRECTION);
        return roleRepository.findByLibelleInAndDeletedOrderByLibelleAsc(libelles, Deletion.NO);
    }


  
    

    public RoleM getRole(Long idRole, List<RoleM> roles){
        for(RoleM role: roles){
            if(idRole == role.getId()){
                return role;
            }
        }
        return null;
    }

    
}
