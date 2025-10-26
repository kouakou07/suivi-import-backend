package com.example.suivie_importBackend.vo;

import com.example.suivie_importBackend.Enum.ModeTransport;
import com.example.suivie_importBackend.models.ResponsableVendeur;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IncotermVO {
    private String incoterm;
    private String signification;
    private Long modeTransportId;
    private Long responsableVendeurId;
}