package com.example.suivie_importBackend.models;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "echeances")
public class Echeance extends BaseM {

    private Long montantApayer;

    @ManyToOne
    @JoinColumn(name = "banque_id")
    private Banque banque;

    @ManyToOne
    @JoinColumn(name = "devise_id")
    private Devise devise;

    @ManyToOne
    @JoinColumn(name = "fournisseur_id")
    private FournisseurM fournisseurM;
}
