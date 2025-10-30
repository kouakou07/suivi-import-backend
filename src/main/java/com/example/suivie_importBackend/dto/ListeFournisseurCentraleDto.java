package com.example.suivie_importBackend.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ListeFournisseurCentraleDto {
    private Long id;
    private String codeFournisseur;
    private String intituleFournisseur;
}
