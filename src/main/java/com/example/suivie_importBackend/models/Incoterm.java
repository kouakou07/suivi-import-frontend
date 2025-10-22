package com.example.suivie_importBackend.models;

import com.example.suivie_importBackend.Enum.ModeTransport;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "intercoterm")
public class Incoterm extends BaseM {
    private String intercoterm;
    private String signification;
    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private ModeTransport modeTransport;
    private String responsableVendeur;
}
