package com.example.suivie_importBackend.dto;

import java.util.List;

public class AssocierFournisseursRequest {

    private List<Long> fournisseursIds;

    public List<Long> getFournisseursIds() { return fournisseursIds; }
    public void setFournisseursIds(List<Long> fournisseursIds) { this.fournisseursIds = fournisseursIds; }
}
