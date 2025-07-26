package com.example.suivie_importBackend.service;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.suivie_importBackend.tools.ReportError;
import com.example.suivie_importBackend.validator.RapportValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import jakarta.validation.Valid;

@Service
public class RapportService {

//    @Autowired
//    UserService userService;
//
//    @Autowired
//    RoleService roleService;
//
//    @Autowired
//    UtilityService utilityService;
//
//    public Object init() {
//        Map<String, Object> output = new HashMap<String, Object>();
//        output.put("etats", EtatCheck.etatWithLabel());
//        return ResponseEntity.ok(output);
//    }
//
//    public Object process(@Valid RapportValidator form, BindingResult res) {
//        if (res.hasErrors()) {
//            return ResponseEntity.badRequest().body(ReportError.getErrors(res));
//        }
//
//        List<Integer> etats = new ArrayList<Integer>();
//        if (form.getEtatCheck() != null) {
//            etats.add(form.getEtatCheck());
//        } else {
//            etats = EtatCheck.all();
//        }
//        Date dateFin = Date.from(
//                form.getDateScanEnd().toInstant().plus(23, ChronoUnit.HOURS)
//                        .plus(59, ChronoUnit.MINUTES)
//                        .plus(59, ChronoUnit.SECONDS));
//        UserM user = userService.getAuthUser();
//        List<ChequeM> cheques;
//        if (roleService.isBankUser(user.getRole().getId())) {
//            cheques = chequeRepository
//                    .findByDateCreationGreaterThanEqualAndDateCreationLessThanEqualAndEtatInAndDeleted(
//                            form.getDateScanStart(), dateFin, etats, Deletion.NO);
//        } else {
//            AgenceM agenceAuth = userService.getCorrecteurAgence(user);
//            List<RemiseM> remises = remiseRepository.findByAgenceAndDeleted(agenceAuth, Deletion.NO);
//            if (remises.size() == 0) {
//                return ReportError.notFound("Aucun cheque trouve");
//            }
//            cheques = chequeRepository
//                    .findByDateCreationGreaterThanEqualAndDateCreationLessThanEqualAndRemiseInAndEtatInAndDeleted(
//                            form.getDateScanStart(), dateFin, remises, etats, Deletion.NO);
//
//        }
//        Double sommeMontant = 0.0;
//        List<ChequeM> checks = new ArrayList<ChequeM>();
//        String codeBankRemettantNum = utilityService.convert_code_bank(form.getBanqueRemettant());
//        String codeBankDebiteurNum = utilityService.convert_code_bank(form.getBanqueDebiteur());
//        for (ChequeM chequeM : cheques) {
//            if (form.getRemise() != null && form.getRemise().length() != 0
//                    && chequeM.getRemise().getReference().equals(form.getRemise()) == false) {
//                continue;
//            }
//
//            if (form.getBanqueRemettant() != null && form.getBanqueRemettant().length() != 0) {
//                System.out.println(form.getBanqueRemettant());
//                String checkcodeBankNumRemettant = utilityService
//                        .convert_code_bank(chequeM.getRemise().getCompteRemettant().getCodeBank());
//                if (checkcodeBankNumRemettant.equals(codeBankRemettantNum) == false) {
//                    continue;
//                }
//            }
//
//            if (form.getAgenceRemettant() != null && form.getAgenceRemettant().length() != 0 && form
//                    .getAgenceRemettant().equals(chequeM.getRemise().getCompteRemettant().getCodeAgence()) == false) {
//                continue;
//            }
//            if (form.getCompteRemettant() != null && form.getCompteRemettant().length() != 0 && form
//                    .getCompteRemettant().equals(chequeM.getRemise().getCompteRemettant().getNumeroCmpt()) == false) {
//                continue;
//            }
//            if (form.getBanqueDebiteur() != null && form.getBanqueDebiteur().length() != 0) {
//                String checkCodeBankDebiteur = utilityService.convert_code_bank(chequeM.getCodeBanque());
//                if (checkCodeBankDebiteur.equals(codeBankDebiteurNum) == false) {
//                    continue;
//                }
//            }
//            if (form.getAgenceDebiteur() != null && form.getAgenceDebiteur().length() != 0
//                    && form.getAgenceDebiteur().equals(chequeM.getCodeAgence()) == false) {
//                continue;
//            }
//            if (form.getCompteDebiteur() != null && form.getCompteDebiteur().length() != 0
//                    && form.getCompteDebiteur().equals(chequeM.getReferenceClient()) == false) {
//                continue;
//            }
//            if (form.getNumeroCheck() != null && form.getNumeroCheck().length() != 0
//                    && form.getNumeroCheck().equals(chequeM.getNumeroCheque()) == false) {
//                continue;
//            }
//            checks.add(chequeM);
//
//            // if (checks.size() != 0 && form.getDateScanStart() != null &&
//            // form.getDateScanEnd() != null) {
//
//            // sommeMontant += chequeM.getMontant();
//
//            // System.out.println(sommeMontant);
//            // }
//
//        }
//        if (checks.size() == 0) {
//            return ReportError.notFound("Aucun cheque trouve");
//        }
//        // montant total des cheques à l'entete du rapport
//        return ResponseEntity.ok(checks);
//
//    }

    // montant total des cheques à l'entete du rapport

}
