package com.example.suivie_importBackend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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

//@Column(name = "banque")
//private Banque banque;

}
