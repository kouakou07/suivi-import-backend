package com.example.suivie_importBackend.service;

import com.example.suivie_importBackend.Enum.Deletion;
import com.example.suivie_importBackend.Mapper.IncotermMapper;
import com.example.suivie_importBackend.dto.IncotermDto;
import com.example.suivie_importBackend.models.Incoterm;
import com.example.suivie_importBackend.models.ModeTransport;
import com.example.suivie_importBackend.models.ResponsableVendeur;
import com.example.suivie_importBackend.repository.IncotermRepository;
import com.example.suivie_importBackend.repository.ModeTransportRepository;
import com.example.suivie_importBackend.repository.ResponsableVendeurRepository;
import com.example.suivie_importBackend.vo.IncotermVO;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class IncotermService {

    private final ModeTransportRepository modeTransportRepository;
    private final ResponsableVendeurRepository responsableVendeurRepository;
    private final IncotermRepository incotermRepository;

    public IncotermService(IncotermRepository incotermRepository, ModeTransportRepository modeTransportRepository, ResponsableVendeurRepository responsableVendeurRepository) {
        this.incotermRepository = incotermRepository;
        this.modeTransportRepository = modeTransportRepository;
        this.responsableVendeurRepository = responsableVendeurRepository;
    }

    public IncotermDto enregistrer(IncotermVO vo) {
        ModeTransport modeTransport = modeTransportRepository.findFirstByIdAndDeleted(vo.getModeTransportId(),Deletion.NO)
                .orElseThrow(() -> new NoSuchElementException("Mode de transport non trouvé"));

        ResponsableVendeur responsableVendeur = responsableVendeurRepository.findFirstByIdAndDeleted(vo.getResponsableVendeurId(),Deletion.NO)
                .orElseThrow(() -> new NoSuchElementException("Responsable vendeur non trouvé"));

        Incoterm entity = IncotermMapper.versEntite(vo, modeTransport, responsableVendeur);
        Incoterm saved = incotermRepository.save(entity);

        return IncotermMapper.versDTO(saved);
    }

    @Transactional(Transactional.TxType.SUPPORTS)
    public List<IncotermDto> listerTous() {
        return incotermRepository.findAllByDeleted(Deletion.NO)
                .stream()
                .map(IncotermMapper::versDTO)
                .toList();
    }

    @Transactional(Transactional.TxType.SUPPORTS)
    public Page<IncotermDto> listerTousAvecPagination(int page) {
        Page<Incoterm> incoterms = incotermRepository.findAllByDeleted(Deletion.NO, PageRequest.of(page, 10));
        return incoterms.map(IncotermMapper::versDTO);
    }

    public IncotermDto modifier(Long id, IncotermVO vo) {
        ModeTransport modeTransport = modeTransportRepository.findById(vo.getModeTransportId())
                .orElseThrow(() -> new NoSuchElementException("Mode de transport non trouvé"));

        ResponsableVendeur responsableVendeur = responsableVendeurRepository.findFirstByIdAndDeleted(vo.getResponsableVendeurId(),Deletion.NO)
                .orElseThrow(() -> new NoSuchElementException("Responsable vendeur non trouvé"));

        return incotermRepository.findByIdAndDeleted(id,Deletion.NO)
                .map(existing -> {
                    existing.setIncoterm(vo.getIncoterm());
                    existing.setSignification(vo.getSignification());
                    existing.setModeTransport(modeTransport);
                    existing.setResponsableVendeur(responsableVendeur);
                    Incoterm updated = incotermRepository.save(existing);
                    return IncotermMapper.versDTO(updated);
                })
                .orElseThrow(() -> new RuntimeException("Incoterm non trouvé"));
    }

    public boolean supprimer(Long id) {
        return incotermRepository.findByIdAndDeleted(id,Deletion.NO)
                .map(incoterm -> {
                    incoterm.setDeleted(true);
                    incotermRepository.save(incoterm);
                    return true;
                })
                .orElse(false);
    }
}
