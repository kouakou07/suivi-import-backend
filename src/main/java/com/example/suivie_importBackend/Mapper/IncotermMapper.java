package com.example.suivie_importBackend.Mapper;


import com.example.suivie_importBackend.dto.IncotermDto;
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
        return IncotermDto.builder()
                .id(incoterm.getId())
                .incoterm(incoterm.getIncoterm())
                .signification(incoterm.getSignification())
                .modeTransportId(incoterm.getModeTransport().getId())
                .responsableVendeurId(incoterm.getResponsableVendeur().getId())
                .build();
    }
}
