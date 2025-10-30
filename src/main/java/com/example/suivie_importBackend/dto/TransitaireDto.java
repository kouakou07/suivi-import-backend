package com.example.suivie_importBackend.dto;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TransitaireDto {
    private Long id;
    private String codeTransitaire;
    private String intituleTransitaire;
    private String nomContact;
    private String telephone;
    private String telecopie;
    private String email;
    private String siret;
    private String nTvaIntracommunautaire;
    private String adresse;
    private String complement;
    private String codePostal;
    private String ville;
    private String region;
    private Long paysId;
    private Long   modePaiementId;
    private Long   echeanceId;
    private String iban;
    private String bic;
    private String contactEmail;
    private String contactFonction;
    private String contactPrenoms;
    private String contactNom;
    private Long  deviseId;
}
