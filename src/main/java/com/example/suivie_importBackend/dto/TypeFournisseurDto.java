package com.example.suivie_importBackend.dto;

import jakarta.validation.constraints.NotBlank;

public record TypeFournisseurDto(
        Long id,
        @NotBlank
        String libelle
) {
}
