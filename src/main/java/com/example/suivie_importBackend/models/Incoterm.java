package com.example.suivie_importBackend.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "intercoterm")
public class Incoterm extends BaseM {

    @Column(nullable = false)
    private String incoterm;

    private String signification;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mode_transport_id", nullable = false)
    private ModeTransport modeTransport;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "responsable_vendeur_id", nullable = false)
    private ResponsableVendeur responsableVendeur;
}
