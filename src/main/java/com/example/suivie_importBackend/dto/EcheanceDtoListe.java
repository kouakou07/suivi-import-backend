package com.example.suivie_importBackend.dto;

import lombok.Builder;

@Builder
public record EcheanceDtoListe (
        Long id,
        Long montantApayer,
        String banqueNom,
        String deviseCode,
        String fournisseurNom
)
{
}


