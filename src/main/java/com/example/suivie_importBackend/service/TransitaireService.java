package com.example.suivie_importBackend.service;

import com.example.suivie_importBackend.Enum.Deletion;
import com.example.suivie_importBackend.dto.TransitaireDto;
import com.example.suivie_importBackend.models.*;
import com.example.suivie_importBackend.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransitaireService {

    private final FournisseurRepository fournisseurRepository;
    private final UserService userService;
    private final ModePaiementRepository modePaiementRepository;
    private final DeviseRepository deviseRepository;
    private final TransitaireRepository transitaireRepository;
    private final PaysRepository paysRepository;
    private final EcheanceRepository echeanceRepository;

    public TransitaireService(FournisseurRepository fournisseurRepository, UserService userService, ModePaiementRepository modePaiementRepository, DeviseRepository deviseRepository, TransitaireRepository transitaireRepository, PaysRepository paysRepository, EcheanceRepository echeanceRepository) {
        this.fournisseurRepository = fournisseurRepository;
        this.userService = userService;
        this.modePaiementRepository = modePaiementRepository;
        this.deviseRepository = deviseRepository;
        this.transitaireRepository = transitaireRepository;
        this.paysRepository = paysRepository;
        this.echeanceRepository = echeanceRepository;
    }

    public List<TransitaireDto> recupererTousLesTransitaires() {
        return transitaireRepository.findAllByDeleted(Deletion.NO)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public Page<TransitaireDto> recupererTousLesTransitairesAvecPagination(int page) {
        Page<Transitaire> transitairePage = transitaireRepository.findAllByDeletedOrderByCodeTransitaireAsc(Deletion.NO, PageRequest.of(page, 20));
        return transitairePage.map(this::mapToDto);
    }

    public String supprimerTransitaire(Long id) {
        Optional<Transitaire> optionalTransitaire = transitaireRepository.findFirstByIdAndDeleted(id, Deletion.NO);
        if (optionalTransitaire.isPresent()) {
            Transitaire transitaire = optionalTransitaire.get();
            transitaire.setDeleted(Deletion.YES);
            transitaireRepository.save(transitaire);
            return "Transitaire supprimé avec succès";
        } else {
            throw new RuntimeException("Transitaire non trouvé ou déjà supprimé");
        }
    }

    @Transactional
    public TransitaireDto creerTransitaire(TransitaireDto dto) {
        Transitaire transitaire = new Transitaire();
        mapToEntity(dto, transitaire);

        transitaire.setDeleted(Deletion.NO);

        if (dto.getModePaiementId() != null) {
            ModePaiement modePaiement = modePaiementRepository.findFirstByIdAndDeleted(dto.getModePaiementId(), Deletion.NO)
                    .orElseThrow(() -> new NoSuchElementException("Mode de paiement non trouvé"));
            transitaire.setModePaiement(modePaiement);
        }

        if (dto.getEcheanceId() != null) {
            Echeance echeance = echeanceRepository.findFirstByIdAndDeleted(dto.getEcheanceId(), Deletion.NO)
                    .orElseThrow(() -> new NoSuchElementException("Échéance non trouvée"));
            transitaire.setEcheance(echeance);
        }

        if (dto.getDeviseId() != null) {
            Devise devise = deviseRepository.findFirstByIdAndDeleted(dto.getDeviseId(), Deletion.NO)
                    .orElseThrow(() -> new NoSuchElementException("Devise non trouvée"));
            transitaire.setDevise(devise);
        }

        if (dto.getPaysId() != null) {
            Pays pays = paysRepository.findFirstByIdAndDeleted(dto.getPaysId(), Deletion.NO)
                    .orElseThrow(() -> new NoSuchElementException("Pays non trouvé"));
            transitaire.setPays(pays);
        }

        Transitaire transitaireCree = transitaireRepository.save(transitaire);
        return mapToDto(transitaireCree);
    }

    public TransitaireDto mettreAJourTransitaire(TransitaireDto dto) {
        Optional<Transitaire> optionalTransitaire = transitaireRepository.findFirstByIdAndDeleted(dto.getId(), Deletion.NO);
        if (optionalTransitaire.isPresent()) {
            Transitaire transitaire = optionalTransitaire.get();
            mapToEntity(dto, transitaire);

            if (dto.getModePaiementId() != null) {
                ModePaiement modePaiement = modePaiementRepository.findFirstByIdAndDeleted(dto.getModePaiementId(), Deletion.NO)
                        .orElseThrow(() -> new NoSuchElementException("Mode de paiement non trouvé"));
                transitaire.setModePaiement(modePaiement);
            }

            if (dto.getEcheanceId() != null) {
                Echeance echeance = echeanceRepository.findFirstByIdAndDeleted(dto.getEcheanceId(), Deletion.NO)
                        .orElseThrow(() -> new NoSuchElementException("Échéance non trouvée"));
                transitaire.setEcheance(echeance);
            }

            if (dto.getDeviseId() != null) {
                Devise devise = deviseRepository.findFirstByIdAndDeleted(dto.getDeviseId(), Deletion.NO)
                        .orElseThrow(() -> new NoSuchElementException("Devise non trouvée"));
                transitaire.setDevise(devise);
            }

            if (dto.getPaysId() != null) {
                Pays pays = paysRepository.findFirstByIdAndDeleted(dto.getPaysId(), Deletion.NO)
                        .orElseThrow(() -> new NoSuchElementException("Pays non trouvé"));
                transitaire.setPays(pays);
            }

            Transitaire transitaireMisAJour = transitaireRepository.save(transitaire);
            return mapToDto(transitaireMisAJour);
        }
        throw new NoSuchElementException("Transitaire non trouvé");
    }

    private TransitaireDto mapToDto(Transitaire t) {
        return TransitaireDto.builder()
                .id(t.getId())
                .codeTransitaire(t.getCodeTransitaire())
                .intituleTransitaire(t.getIntituleTransitaire())
                .nomContact(t.getNomContact())
                .telephone(t.getTelephone())
                .telecopie(t.getTelecopie())
                .email(t.getEmail())
                .siret(t.getSiret())
                .nTvaIntracommunautaire(t.getNTvaIntracommunautaire())
                .adresse(t.getAdresse())
                .complement(t.getComplement())
                .codePostal(t.getCodePostal())
                .ville(t.getVille())
                .region(t.getRegion())
                .paysId(t.getPays() != null ? t.getPays().getId() : null)
                .modePaiementId(t.getModePaiement() != null ? t.getModePaiement().getId() : null)
                .echeanceId(t.getEcheance() != null ? t.getEcheance().getId() : null)
                .iban(t.getIban())
                .bic(t.getBic())
                .contactEmail(t.getContactEmail())
                .contactFonction(t.getContactFonction())
                .contactPrenoms(t.getContactPrenoms())
                .contactNom(t.getContactNom())
                .deviseId(t.getDevise() != null ? t.getDevise().getId() : null)
                .build();
    }

    private void mapToEntity(TransitaireDto dto, Transitaire t) {
        t.setCodeTransitaire(dto.getCodeTransitaire());
        t.setIntituleTransitaire(dto.getIntituleTransitaire());
        t.setNomContact(dto.getNomContact());
        t.setTelephone(dto.getTelephone());
        t.setTelecopie(dto.getTelecopie());
        t.setEmail(dto.getEmail());
        t.setSiret(dto.getSiret());
        t.setNTvaIntracommunautaire(dto.getNTvaIntracommunautaire());
        t.setAdresse(dto.getAdresse());
        t.setComplement(dto.getComplement());
        t.setCodePostal(dto.getCodePostal());
        t.setVille(dto.getVille());
        t.setRegion(dto.getRegion());
        t.setIban(dto.getIban());
        t.setBic(dto.getBic());
        t.setContactEmail(dto.getContactEmail());
        t.setContactFonction(dto.getContactFonction());
        t.setContactPrenoms(dto.getContactPrenoms());
        t.setContactNom(dto.getContactNom());
    }
}
