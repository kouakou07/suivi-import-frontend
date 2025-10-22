package com.example.suivie_importBackend.models;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "banques")
public class Banque extends BaseM{
    private String libelle;
    private String numeroCompte;
}
