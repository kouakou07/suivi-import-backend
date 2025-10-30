package com.example.suivie_importBackend.Mapper;


import com.example.suivie_importBackend.dto.IncotermDto;
import com.example.suivie_importBackend.dto.ModeTransportDto;
import com.example.suivie_importBackend.dto.ResponsableVendeurDto;
import com.example.suivie_importBackend.models.Incoterm;
import com.example.suivie_importBackend.models.ModeTransport;
import com.example.suivie_importBackend.models.ResponsableVendeur;
import com.example.suivie_importBackend.vo.IncotermVO;

public class IncotermMapper {


    public static Incoterm versEntite(IncotermVO vo, ModeTransport modeTransport, ResponsableVendeur responsableVendeur) {
        return Incoterm.builder()
                .incoterm(vo.getIncoterm())
                .signification(vo.getSignification())
                .modeTransport(modeTransport)
                .responsableVendeur(responsableVendeur)
                .build();
    }

    public static IncotermDto versDTO(Incoterm incoterm) {
        return new IncotermDto(
                incoterm.getId(),
                incoterm.getIncoterm(),
                incoterm.getSignification(),
                incoterm.getModeTransport() != null ? incoterm.getModeTransport().getId() : null,
                incoterm.getResponsableVendeur() != null ? incoterm.getResponsableVendeur().getId() : null,
                incoterm.getModeTransport() != null
                        ? new ModeTransportDto(
                        incoterm.getModeTransport().getId(),
                        incoterm.getModeTransport().getIntitule()
                )
                        : null,
                incoterm.getResponsableVendeur() != null
                        ? new ResponsableVendeurDto(
                        incoterm.getResponsableVendeur().getId(),
                        incoterm.getResponsableVendeur().getLibelle()
                )
                        : null
        );
    }
}
