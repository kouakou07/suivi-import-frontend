package com.example.suivie_importBackend.service;

import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.example.suivie_importBackend.Enum.Deletion;
import com.example.suivie_importBackend.Enum.Password;
import com.example.suivie_importBackend.Enum.Status;
import com.example.suivie_importBackend.models.RoleM;
import com.example.suivie_importBackend.models.UserM;
import com.example.suivie_importBackend.repository.RoleRepository;
import com.example.suivie_importBackend.repository.UserRepository;
import com.example.suivie_importBackend.tools.ReportError;
import com.example.suivie_importBackend.validator.LoginForm;
import com.example.suivie_importBackend.validator.PasswordForm;
import com.example.suivie_importBackend.validator.UserBankForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleService roleService;

    @Autowired
    UtilityService utilityService;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    JwtService jwtService;

    @Autowired
    ParameterService parameterService;

    @Autowired
    AuditService auditService;

    @Autowired
    HttpServletRequest httpServletRequest;

   

    // user authenticated
    public UserM getAuthUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Optional<UserM> user = this.userRepository.findFirstByUsernameAndStatusAndDeleted(username, Status.DEFAULT_ENABLE, Deletion.NO);
        return user.isPresent() ? user.get() : null;
    }

    //login
    public Object login(@Valid LoginForm form, BindingResult res){

        if(res.hasErrors()){
            return ResponseEntity.badRequest().body(ReportError.getErrors(res));
        } 
        Optional<UserM> userOptional = userRepository.findFirstByUsernameAndDeleted(form.getUsername().toUpperCase(), Deletion.NO);
        Boolean credential_error = false;
        UserM  user = null;
        Map<String, String> error = new HashMap<String, String>();
        
        if(userOptional.isEmpty()){
            credential_error = true;
        }else{
            user = userOptional.get();
            if(!BCrypt.checkpw(form.getPassword(), user.getPassword())){
                credential_error = true;
                this.monitorForcingConnection(user);

            }
        }
        if(credential_error){
            error.put("credential_error", "Nom utilisateur ou mot de passe incorrect"); 
            return ResponseEntity.badRequest().body(error);
        }else{

            if(user.getStatus() == Status.DEFAULT_DISABLE){
                error.put("credential_error", "Votre compte a ete desactive, Veuillez contacter votre administrateur");
                return ResponseEntity.badRequest().body(error);
            }
            
            user.setEssaie(0);
            Date now = new Date();
            user.setDateConnection(now);
            user.setDateActivity(Date.from(now.toInstant().plus(parameterService.getInactivityTime(), ChronoUnit.MINUTES)));
            user = userRepository.save(user);
            this.auditLogin(user.getUsername() + " authentifie", user.toString());
            String tokenAcces = jwtService.generateToken(user.getUsername(), "user");
            if(user.getOld() == null || user.getOld() == Password.UNCHANGED){
                error.put("unchanged_password", "Vous devez modifier le mot de passe pour avoir acces a la plateforme");
                error.put("token", tokenAcces);
                return ReportError.forbidden(error);
            }
            Map<String, Object> output = new HashMap<String, Object>();
            output.put("user", user);
            output.put("token", tokenAcces);
            return ResponseEntity.ok(output);
        }
    }


    //profil
    public Object myProfile(){
        return ResponseEntity.ok(this.getAuthUser());
    }

    //change password
    public Object changePassword(@Valid PasswordForm form, BindingResult res){
        if(res.hasErrors()){
            return ResponseEntity.badRequest().body(ReportError.getErrors(res));
        }
        UserM user = this.getAuthUser();
        user.setPassword(form.getPassword());
        user = this.userRepository.save(user);
        this.auditImportant(user.getUsername() + " Le mot de passe a ete change", user.toString());
        return ResponseEntity.ok(user);
    }

    public Object changePasswordAtLogin(@Valid PasswordForm form, BindingResult res){
        String bearToken = httpServletRequest.getHeader("Authorization");
        String tokenName = "Bearer ";
        String username = "";
        if(bearToken != null && bearToken.startsWith(tokenName)){
            String token = bearToken.substring(tokenName.length());
            username = jwtService.getUsernameFromToken(token);
        }
        if(res.hasErrors()){
            return ResponseEntity.badRequest().body(ReportError.getErrors(res));
        }
        //Optional<UserM> userOpt = userRepository.findFirstByUsernameAndStatusAndDeleted(username, Status.DEFAULT_ENABLE, Deletion.NO);
        Optional<UserM> userOpt = userRepository.findFirstByUsernameAndStatusAndOldAndDeleted(username, Status.DEFAULT_ENABLE, Password.UNCHANGED, Deletion.NO);
       
        if(userOpt.isEmpty()){
            return ReportError.notFound("Utilisateur n\'existe pas ou son mot de passe a ete deja modifie a sa premiere connexion");
        }
        UserM user = userOpt.get();
        user.setPassword(form.getPassword());
        user.setOld(Password.CHANGED);
        user = this.userRepository.save(user);
        this.auditImportant(user.getUsername() + " Le mot de passe a ete change", user.toString());
        return ResponseEntity.ok(user);
    }

    public Object listBankUsers(Integer page){
        List<RoleM> roles = roleService.bankUser();
        UserM auth = this.getAuthUser();
        Page<UserM> users = userRepository.findByIdNotAndRoleInAndDeletedOrderByNomAsc(auth.getId(), roles, Deletion.NO, PageRequest.of(page, 25));
        return ResponseEntity.ok(users);
    }

    //create user of ecobank
    public Object createBankUser(@Valid UserBankForm form, BindingResult res){
        if(res.hasErrors()){
            return ResponseEntity.badRequest().body(ReportError.getErrors(res));
        }
        Boolean isRoleUserBank = roleService.isBankUser(form.getRole());
        Boolean usernameExists = userRepository.existsByUsernameAndDeleted(form.getUsername().toUpperCase(), Deletion.NO);
        if(!isRoleUserBank || usernameExists){
            Map<String, Object> errors = new HashMap<String, Object>();
            if(!isRoleUserBank){
                errors.put("role", "Le role selectionne est incorrect");
            }
            if(usernameExists){
                errors.put("username", "Le nom utilisateur existe deja");
            }
            return ResponseEntity.badRequest().body(errors);
        }
        RoleM role = roleRepository.findByIdAndDeleted(form.getRole(), false);
        UserM newUser = new UserM(form.getUsername().toUpperCase(), null, form.getEmail(), form.getNom(), form.getPrenom(), role);
        String rawPassword = utilityService.generatePassword();
        newUser.setPassword(rawPassword);
        newUser = this.userRepository.save(newUser);
        Map<String, Object> output = new HashMap<String, Object>();
        output.put("passwordGenerated", rawPassword);
        output.put("user", newUser);
        this.auditImportant("Nouveau utilisateur ajoute", newUser.toString());
        return ResponseEntity.ok(output);
    }

    //info user ecobank
    public Object infoUserBank(Long userId){
        List<RoleM> roles = roleService.bankUser();
        Optional<UserM> user = userRepository.findFirstByIdAndRoleInAndDeleted(userId, roles, Deletion.NO);
        if(user.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Map<String, Object> output = new HashMap<String, Object>();
        output.put("roles", roles);
        output.put("user", user.get());
        return ResponseEntity.ok(output);
    }

    public Object editBankUser(@Valid UserBankForm form, BindingResult res){
        if(res.hasErrors()){
            return ResponseEntity.badRequest().body(ReportError.getErrors(res));
        }
        List<RoleM> roles = roleService.bankUser();
        Optional<UserM> user = userRepository.findFirstByUsernameAndRoleInAndDeleted(form.getUsername().toUpperCase(), roles, Deletion.NO);
        if(user.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Boolean isRoleUserBank = roleService.isBankUser(form.getRole());
        if(isRoleUserBank == false){
            Map<String, Object> errors = new HashMap<String, Object>();
            errors.put("role", "Le role n\'existe pas");
            return ResponseEntity.badRequest().body(errors);
        }
        UserM ecobankUser = user.get();
        ecobankUser.update(form.getNom(), form.getPrenom(), form.getEmail(), roleService.getRole(form.getRole(), roles));
        ecobankUser = userRepository.save(ecobankUser);
        this.auditImportant("edition utilisateur", ecobankUser.toString());
        return ResponseEntity.ok(ecobankUser);
    }


    public Object resetPassword(Long userId){
        Optional<UserM> userOpt = this.userRepository.findFirstByIdAndDeleted(userId, Deletion.NO);
        if(userOpt.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        String newPassword = this.utilityService.generatePassword();
        UserM user = userOpt.get();
        user.setPassword(newPassword);
        user.setOld(Password.UNCHANGED);
        user = this.userRepository.save(user);
        Map<String, Object> output = new HashMap<String, Object>();
        output.put("password", newPassword);
        output.put("user", userOpt.get());
        this.auditImportant("Mot de passe reinitialise", user.toString());
        return ResponseEntity.ok(output);
    }

    public Object changeStatus(Long userId){
        Optional<UserM> userOpt = this.userRepository.findFirstByIdAndDeleted(userId, Deletion.NO);
        if(userOpt.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        UserM user = userOpt.get();
        if(user.getStatus() == 1){
            user.setStatus(0);
        }else{
            user.setStatus(1);
            user.setEssaie(0);
        }
        user = this.userRepository.save(user);
        this.auditImportant("Changement de status", user.toString());
        return ResponseEntity.ok(user.getStatus() == 1);
    }

    public Integer monitorForcingConnection(UserM user){
        Integer essaie = user.getEssaie() + 1;
        Integer maxAttempt = parameterService.getAttemptConnection();
        if(maxAttempt <= essaie){
            user.setStatus(Status.DEFAULT_DISABLE);
            user.updateConnection(essaie);
            this.userRepository.save(user);
            return 0;
        }
        user.updateConnection(essaie);
        this.userRepository.save(user);
        return 1;

    }

    public void auditInfo(String libelle, String object){
        UserM user = this.getAuthUser();
        auditService.recordInfo(libelle, object, user);
    }

    public void auditImportant(String libelle, String object){
        UserM user = this.getAuthUser();
        auditService.recordImportant(libelle, object, user);
    }

    public void auditImportant(String libelle, String object,  UserM user){
        auditService.recordImportant(libelle, object, user);
    }

    public void auditImportantUser(String libelle, String object, UserM user){
        auditService.recordImportant(libelle, object, user);
    }

    public void auditDanger(String libelle, String object){
        UserM user = this.getAuthUser();
        auditService.recordDanger(libelle, object, user);
    }

    public void auditLogin(String libelle, String object){
        UserM user = this.getAuthUser();
        auditService.recordLogin(libelle, object, user);
    }
}
