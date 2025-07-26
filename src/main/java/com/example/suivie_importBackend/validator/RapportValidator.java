package com.example.suivie_importBackend.validator;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.validation.constraints.NotNull;

public class RapportValidator {
    
    private String remise;
    private String agenceRemettant;
    private String compteRemettant;
    private String banqueRemettant;
    private String banqueDebiteur;
    private String agenceDebiteur;
    private String compteDebiteur;
    private String numeroCheck;
    private Integer etatCheck;
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date dateScanStart;
    
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date dateScanEnd;

    public String getRemise() {
        return remise;
    }
    public void setRemise(String remise) {
        if(remise != null){
            this.remise = remise.trim() == "" ? null : remise.trim();
        }else{
            this.remise = remise;
        }
        
    }
    public String getAgenceRemettant() {
        return agenceRemettant;
    }
    public void setAgenceRemettant(String agenceRemettant) {
        if(agenceRemettant != null){
            this.agenceRemettant = agenceRemettant.trim() == "" ? null : agenceRemettant.trim();
        }else{
            this.agenceRemettant = agenceRemettant;
        }
        
    }
    public String getCompteRemettant() {
        return compteRemettant;
    }
    public void setCompteRemettant(String compteRemettant) {
        if(compteRemettant != null){
            this.compteRemettant = compteRemettant.trim() == "" ? null : compteRemettant.trim();
        }else{
            this.compteRemettant = compteRemettant;
        }
        
    }
    public String getBanqueRemettant() {
        return banqueRemettant;
    }
    public void setBanqueRemettant(String banqueRemettant) {
        if(banqueRemettant != null){
            this.banqueRemettant = banqueRemettant.trim() == "" ? null : banqueRemettant.trim();
        }else{
            this.banqueRemettant = banqueRemettant;
        }
        
    }
    public String getBanqueDebiteur() {
        return banqueDebiteur;
    }
    public void setBanqueDebiteur(String banqueDebiteur) {
        if(banqueDebiteur != null){
            this.banqueDebiteur = banqueDebiteur.trim() == "" ? null : banqueDebiteur.trim();
        }else{
            this.banqueDebiteur = banqueDebiteur;
        }
        
    }
    public String getAgenceDebiteur() {
        return agenceDebiteur;
    }
    public void setAgenceDebiteur(String agenceDebiteur) {
        if(agenceDebiteur != null){
            this.agenceDebiteur = agenceDebiteur.trim() == "" ? null : agenceDebiteur.trim();
        }else{
            this.agenceDebiteur = agenceDebiteur;
        }
       
    }
    public String getCompteDebiteur() {
        return compteDebiteur;
    }
    public void setCompteDebiteur(String compteDebiteur) {
        if(compteDebiteur != null){
            this.compteDebiteur = compteDebiteur.trim() == "" ? null : compteDebiteur.trim();
        }else{
            this.compteDebiteur = compteDebiteur;
        }
        
    }
    public String getNumeroCheck() {
        return numeroCheck;
    }
    public void setNumeroCheck(String numeroCheck) {
        if(numeroCheck != null){
            this.numeroCheck = numeroCheck.trim() == "" ? null : numeroCheck.trim();
        }else{
            this.numeroCheck = numeroCheck;
        }
        
    }
    public Integer getEtatCheck() {
        return etatCheck;
    }
    public void setEtatCheck(Integer etatCheck) {
        this.etatCheck = etatCheck;
    }
    public Date getDateScanStart() {
        return dateScanStart;
    }
    public void setDateScanStart(Date dateScanStart) {
        this.dateScanStart = dateScanStart;
    }
    public Date getDateScanEnd() {
        return dateScanEnd;
    }
    public void setDateScanEnd(Date dateScanEnd) {
        this.dateScanEnd = dateScanEnd;
    }

    
}
