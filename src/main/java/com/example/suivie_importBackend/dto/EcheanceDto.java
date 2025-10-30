package com.example.suivie_importBackend.dto;

public record EcheanceDto(
        Long id,
        Long montantApayer,
        Long banqueId,
        Long deviseId,
        Long fournisseurId
) {
}
