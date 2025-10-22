package com.example.suivie_importBackend.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "fdi")
public class Fdi extends BaseM{

    private String numeroFdi;

    private LocalDate dateFdi;

    private String transitaire;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "commande_id", nullable = false)
    private CommandeM commande;
}
