package com.example.suivie_importBackend.dto;

import com.example.suivie_importBackend.models.Devise;
import com.example.suivie_importBackend.models.ModePaiement;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.util.Date;

public class TransitaireDtoListe {

    private String codeTransitaire;
    private String intituleTransitaire;
    private Date dateCreation;
    private String nomContact;
    private String telephone;
    private String telecopie;
    @Column(name = "e_mail")
    private String email;
    private String siret;
    @Column(name = "n_tva_intracommunautaire")
    private String nTvaIntracommunautaire;
    private String adresse;
    private String complement;
    @Column(name = "code_postal")
    private String codePostal;
    private String ville;
    private String region;
    private String pays;

    @ManyToOne
    @JoinColumn(name = "mode_paiement_id")
    private ModePaiement modePaiement;
//    @ManyToOne
//    @JoinColumn(name = "echeance_id")
//    private Echeance echeance;

    private String iban;
    private String bic;
    @Column(name = "contact_Email")
    private String contactEmail;
    @Column(name = "contact_Fonction")
    private String contactFonction;
    @Column(name = "contact_Prenoms")
    private String contactPrenoms;
    @Column(name = "contact_Nom")
    private String contactNom;

    @ManyToOne
    @JoinColumn(name = "devise_id")
    private Devise devise;
}
