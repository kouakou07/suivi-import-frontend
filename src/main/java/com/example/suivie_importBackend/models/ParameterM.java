package com.example.suivie_importBackend.models;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "parametres")
public class ParameterM extends BaseM {
    private String code;
    private String value;

    public ParameterM() {
    }

    public ParameterM(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
        this.dateEdition = new Date();
    }

}
