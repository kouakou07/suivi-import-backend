package com.example.suivie_importBackend.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class IncotermDto {
    private Long id;
    private String incoterm;
    private String signification;
    private Long modeTransportId;
    private Long responsableVendeurId;
    private ModeTransportDto modeTransport;
    private ResponsableVendeurDto responsableVendeur;
}
