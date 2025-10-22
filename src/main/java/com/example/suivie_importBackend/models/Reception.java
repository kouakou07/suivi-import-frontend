package com.example.suivie_importBackend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "receptions")
public class Reception extends  BaseM {

    private String numeroBl;
    private LocalDate dateEntree;
    private Integer quantiteRecue;
    private Boolean conforme;

    @ManyToOne
    @JoinColumn(name = "commande_id")
    private CommandeM commande;
}
