package com.example.suivie_importBackend.dto;


import com.example.suivie_importBackend.Enum.ModeTransport;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class IncotermDto {
    private Long id;
    private String intercoterm;
    private String signification;
    private ModeTransport modeTransport;
    private String responsableVendeur;
}
