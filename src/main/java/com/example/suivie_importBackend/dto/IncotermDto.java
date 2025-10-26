package com.example.suivie_importBackend.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class IncotermDto {
    Long id;
    String incoterm;
    String signification;
    Long modeTransportId;
    Long responsableVendeurId;
}
