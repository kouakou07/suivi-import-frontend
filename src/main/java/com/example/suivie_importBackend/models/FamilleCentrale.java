package com.example.suivie_importBackend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "familleCentrales")
public class FamilleCentrale extends BaseM {

   private  String intitule;
}
