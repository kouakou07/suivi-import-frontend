package com.example.suivie_importBackend.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "fournisseurs_centrales")
public class FournisseurCentraleM extends BaseM {

    private Long id;

    @Column(name = "id_fournisseur_centrale")
    private Long idFournisseurCentrale;

    @Column(name = "id_fournisseur")
    private Long idFournisseur;

}
