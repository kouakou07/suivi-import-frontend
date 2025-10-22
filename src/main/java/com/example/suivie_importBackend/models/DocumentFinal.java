package com.example.suivie_importBackend.models;

import com.example.suivie_importBackend.Enum.TypeDocument;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "documents_finaux")
public class DocumentFinal  extends BaseM{

    @Enumerated(EnumType.STRING)
    private TypeDocument typeDoc;

    private String numero;
    private LocalDate dateDoc;
    private String lienDrive;

    @ManyToOne
    @JoinColumn(name = "commande_id")
    private CommandeM commande;
}
