package com.example.suivie_importBackend.service;

import com.example.suivie_importBackend.Enum.Deletion;
import com.example.suivie_importBackend.dto.*;
import com.example.suivie_importBackend.models.*;
import com.example.suivie_importBackend.repository.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

@Service
public class ParametreService {

    private final PaysRepository paysRepository;
    private final ModeEnvoiRepository modeEnvoiRepository;
    private final ModePaiementRepository modePaiementRepository;
    private final DepartementRepository departementRepository;
    private final DeviseRepository deviseRepository;
    private final FamilleCentraleRepository familleCentraleRepository;
    private final TypeFournisseurRepository typeFournisseurRepository;
    private final ModeTransportRepository modeTransportRepository;
    private final ResponsableVendeurRepository responsableVendeurRepository;

    public ParametreService(PaysRepository paysRepository, ModeEnvoiRepository modeEnvoiRepository, ModePaiementRepository modePaiementRepository, DepartementRepository departementRepository, DeviseRepository deviseRepository, FamilleCentraleRepository familleCentraleRepository, TypeFournisseurRepository typeFournisseurRepository, ModeTransportRepository modeTransportRepository, ResponsableVendeurRepository responsableVendeurRepository) {
        this.paysRepository = paysRepository;
        this.modeEnvoiRepository = modeEnvoiRepository;
        this.modePaiementRepository = modePaiementRepository;
        this.departementRepository = departementRepository;
        this.deviseRepository = deviseRepository;
        this.familleCentraleRepository = familleCentraleRepository;
        this.typeFournisseurRepository = typeFournisseurRepository;
        this.modeTransportRepository = modeTransportRepository;
        this.responsableVendeurRepository = responsableVendeurRepository;
    }

    @Transactional
    public ResponseEntity<Pays> creerPays(@RequestBody Pays pays) {
        Pays pays1 = new Pays();
        pays1.setDeleted(Deletion.NO);
        Pays enregistrePays = paysRepository.save(pays);
        return ResponseEntity.status(HttpStatus.CREATED).body(enregistrePays);
    }

    @Transactional
    public PaysDto mettreAJourPays(PaysDto paysDto) {
        Optional<Pays> optionalPays = paysRepository.findFirstByIdAndDeleted(paysDto.id(), Deletion.NO);
        if (optionalPays.isPresent()) {
            Pays pays = optionalPays.get();
            pays.setIntitule(paysDto.intitule());
            Pays paysMisAJour = paysRepository.save(pays);
            return new PaysDto(paysMisAJour.getId(), paysMisAJour.getIntitule());
        }
        throw new NoSuchElementException("Pays non trouvé");
    }

    @Transactional(readOnly = true)
    public Page<PaysDtoList> recupererPays(Pageable pageable) {
        return paysRepository.findAllByDeleted(Deletion.NO, pageable)
                .map(c -> PaysDtoList.builder()
                        .id(c.getId())
                        .intitule(c.getIntitule())
                        .build()
                );
    }

    public boolean supprimerPays(Long id) {
        Optional<Pays> optionalPays = paysRepository.findFirstByIdAndDeleted(id, Deletion.NO);
        if (optionalPays.isPresent()) {
            Pays recupererPay = optionalPays.get();
            recupererPay.setDeleted(Deletion.YES);
            paysRepository.save(recupererPay);
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    public ModeEnvoi creerModeEnvoi(@RequestBody ModeEnvoiDto modeEnvoi) {
        ModeEnvoi modeEnvoi1 = new ModeEnvoi();
        modeEnvoi1.setIntitule(modeEnvoi.intitule());
        modeEnvoi1.setDeleted(Deletion.NO);
        return modeEnvoiRepository.save(modeEnvoi1);
    }

    @Transactional
    public ModeEnvoiDto mettreAJourModeEnvoi(Long id, ModeEnvoiDto modeEnvoiDto) {
        ModeEnvoi mode = modeEnvoiRepository.findFirstByIdAndDeleted(id, Deletion.NO)
                .orElseThrow(() -> new NoSuchElementException("Mode d’envoi non trouvé"));

        mode.setIntitule(modeEnvoiDto.intitule());
        ModeEnvoi modeMisAJour = modeEnvoiRepository.save(mode);

        return new ModeEnvoiDto(modeMisAJour.getId(), modeMisAJour.getIntitule());
    }

    @Transactional(readOnly = true)
    public Page<ModeEnvoiListDto> recupererModeEnvoi(int page) {
        return modeEnvoiRepository.findAllByDeleted(Deletion.NO, PageRequest.of(page, 10))
                .map(c -> ModeEnvoiListDto.builder()
                        .id(c.getId())
                        .intitule(c.getIntitule())
                        .build()
                );
    }

    public boolean supprimerModeEnvoi(Long id) {
        Optional<ModeEnvoi> optionalModeEnvoi = modeEnvoiRepository.findFirstByIdAndDeleted(id, Deletion.NO);
        if (optionalModeEnvoi.isPresent()) {
            ModeEnvoi recupererModeEnvoi = optionalModeEnvoi.get();
            recupererModeEnvoi.setDeleted(Deletion.YES);
            modeEnvoiRepository.save(recupererModeEnvoi);
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    public Devise creerDevise(@RequestBody DeviseDto devise) {
        Devise devise1 = new Devise();
        devise1.setIntitule(devise.intitule());
        devise1.setDeleted(Deletion.NO);
        return deviseRepository.save(devise1);
    }

    @Transactional
    public DeviseDto mettreAJourDevise(DeviseDto deviseDto) {
        Optional<Devise> optionalDevise = deviseRepository.findFirstByIdAndDeleted(deviseDto.id(), Deletion.NO);
        if (optionalDevise.isPresent()) {
            Devise devise = optionalDevise.get();
            devise.setIntitule(deviseDto.intitule());
            Devise deviseMisAJour = deviseRepository.save(devise);
            return new DeviseDto(deviseMisAJour.getId(), deviseMisAJour.getIntitule());
        }
        throw new NoSuchElementException("Devise non trouvé");
    }

    @Transactional(readOnly = true)
    public Page<DeviseDtoList> recupererDevise(int page) {
        return deviseRepository.findAllByDeleted(Deletion.NO, PageRequest.of(page, 10))
                .map(c -> DeviseDtoList.builder()
                        .id(c.getId())
                        .intitule(c.getIntitule())
                        .build()
                );
    }

    public boolean supprimerDevise(Long id) {
        Optional<Devise> optionalDevise = deviseRepository.findFirstByIdAndDeleted(id, Deletion.NO);
        if (optionalDevise.isPresent()) {
            Devise recupererDevise = optionalDevise.get();
            recupererDevise.setDeleted(Deletion.YES);
            deviseRepository.save(recupererDevise);
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    public FamilleCentrale creerFamilleCentrale(@RequestBody FamilleCentrale familleCentrale) {
        FamilleCentrale familleCentrale1 = new FamilleCentrale();
        familleCentrale1.setIntitule(familleCentrale.getIntitule());
        familleCentrale1.setDeleted(Deletion.NO);
        return familleCentraleRepository.save(familleCentrale1);

    }

    @Transactional
    public FamilleCentraleDto mettreAJourFamilleCentrale(Long id, FamilleCentraleDto familleCentraleDto) {
        Optional<FamilleCentrale> optionalFamilleCentrale = familleCentraleRepository.findFirstByIdAndDeleted(id, Deletion.NO);
        if (optionalFamilleCentrale.isPresent()) {
            FamilleCentrale familleCentrale = optionalFamilleCentrale.get();
            familleCentrale.setIntitule(familleCentraleDto.intitule());
            FamilleCentrale familleCentraleMisAJour = familleCentraleRepository.save(familleCentrale);
            return new FamilleCentraleDto(familleCentraleMisAJour.getId(), familleCentraleMisAJour.getIntitule());
        }
        throw new NoSuchElementException("Devise non trouvé");
    }

    @Transactional(readOnly = true)
    public Page<FamilleCentraleDtoList> recupererFamilleCentrale(int page) {
        return familleCentraleRepository.findAllByDeleted(Deletion.NO, PageRequest.of(page, 10))
                .map(c -> FamilleCentraleDtoList.builder()
                        .id(c.getId())
                        .intitule(c.getIntitule())
                        .build()
                );
    }

    public boolean supprimerFamilleCentrale(Long id) {
        Optional<FamilleCentrale> optionalFamilleCentrale = familleCentraleRepository.findFirstByIdAndDeleted(id, Deletion.NO);
        if (optionalFamilleCentrale.isPresent()) {
            FamilleCentrale familleCentrale = optionalFamilleCentrale.get();
            familleCentrale.setDeleted(Deletion.YES);
            familleCentraleRepository.save(familleCentrale);
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    public TypeFournisseur creerTypeFournisseur(@RequestBody TypeFournisseurDto typeFournisseur) {
        TypeFournisseur enregistrer = new TypeFournisseur();
        enregistrer.setLibelle(typeFournisseur.libelle());
        enregistrer.setDeleted(Deletion.NO);
        return typeFournisseurRepository.save(enregistrer);

    }

    @Transactional
    public TypeFournisseurDto mettreAJourTypeFournisseur(Long id, TypeFournisseurDto dto) {
        TypeFournisseur entity = typeFournisseurRepository.findFirstByIdAndDeleted(id, Deletion.NO)
                .orElseThrow(() -> new NoSuchElementException("Type fournisseur non trouvé"));

        entity.setLibelle(dto.libelle());
        TypeFournisseur updated = typeFournisseurRepository.save(entity);

        return new TypeFournisseurDto(updated.getId(), updated.getLibelle());
    }

    @Transactional(readOnly = true)
    public Page<TypeFournisseurDtoListe> recupererTypeFournisseur(int page) {
        return typeFournisseurRepository.findAllByDeleted(Deletion.NO, PageRequest.of(page, 10))
                .map(c -> TypeFournisseurDtoListe.builder()
                        .id(c.getId())
                        .libelle(c.getLibelle())
                        .build()
                );
    }

    public boolean supprimerTypeFournisseur(Long id) {
        return typeFournisseurRepository.findFirstByIdAndDeleted(id, Deletion.NO)
                .map(tf -> {
                    tf.setDeleted(Deletion.YES);
                    typeFournisseurRepository.save(tf);
                    return true;
                })
                .orElse(false);
    }

    @Transactional
    public Departement creerDepartement(@RequestBody DepartementDto departementDto) {
        Departement enregistrer = new Departement();
        enregistrer.setIntitule(departementDto.intitule());
        return departementRepository.save(enregistrer);
    }

    @Transactional
    public DepartementDto mettreAJourDepartement(Long id, DepartementDto dto) {
        Departement entity = departementRepository.findFirstByIdAndDeleted(id, Deletion.NO)
                .orElseThrow(() -> new NoSuchElementException("le département non trouvé"));
        entity.setIntitule(dto.intitule());
        Departement updated = departementRepository.save(entity);
        return new DepartementDto(updated.getId(), updated.getIntitule());
    }

    @Transactional(readOnly = true)
    public Page<DepartementDtoListe> recupererDepartement(int page) {
        return departementRepository.findAllByDeleted(Deletion.NO, PageRequest.of(page, 10))
                .map(c -> DepartementDtoListe.builder()
                        .id(c.getId())
                        .intitule(c.getIntitule())
                        .build()
                );
    }

    public boolean supprimerDepartement(Long id) {
        return departementRepository.findFirstByIdAndDeleted(id, Deletion.NO)
                .map(departement -> {
                    departement.setDeleted(Deletion.YES);
                    departementRepository.save(departement);
                    return true;
                })
                .orElse(false);
    }

    @Transactional
    public ModePaiement creerModePaiement(@RequestBody ModePaiementDto modePaiementDto) {
        ModePaiement enregistrer = new ModePaiement();
        enregistrer.setIntitule(modePaiementDto.intitule());
        return modePaiementRepository.save(enregistrer);
    }

    @Transactional
    public ModePaiementDto mettreAJourModePaiement(Long id, ModePaiementDto dto) {
        ModePaiement entity = modePaiementRepository.findFirstByIdAndDeleted(id, Deletion.NO)
                .orElseThrow(() -> new NoSuchElementException("le mode de paiement non trouvé"));
        entity.setIntitule(dto.intitule());
        ModePaiement updated = modePaiementRepository.save(entity);
        return new ModePaiementDto(updated.getId(), updated.getIntitule());
    }

    @Transactional(readOnly = true)
    public Page<ModePaiementDtoListe> recupererModePaiement(int page) {
        return modePaiementRepository.findAllByDeleted(Deletion.NO, PageRequest.of(page, 10))
                .map(c -> ModePaiementDtoListe.builder()
                        .id(c.getId())
                        .intitule(c.getIntitule())
                        .build()
                );
    }

    public boolean supprimerModePaiement(Long id) {
        return modePaiementRepository.findFirstByIdAndDeleted(id, Deletion.NO)
                .map(modePaiement -> {
                    modePaiement.setDeleted(Deletion.YES);
                    modePaiementRepository.save(modePaiement);
                    return true;
                })
                .orElse(false);
    }

    @Transactional
    public ModeTransport creerModeTransport(@RequestBody ModeTransportDto modeTransportDto) {
        ModeTransport enregistrer = new ModeTransport();
        enregistrer.setIntitule(modeTransportDto.intitule());
        return modeTransportRepository.save(enregistrer);
    }

    @Transactional
    public ModeTransportDto mettreAJourModeTransport(Long id, ModeTransportDto dto) {
        ModeTransport entity = modeTransportRepository.findFirstByIdAndDeleted(id, Deletion.NO)
                .orElseThrow(() -> new NoSuchElementException("le mode de transport non trouvé"));
        entity.setIntitule(dto.intitule());
        ModeTransport updated = modeTransportRepository.save(entity);
        return new ModeTransportDto(updated.getId(), updated.getIntitule());
    }

    @Transactional(readOnly = true)
    public Page<ModeTransportDtoListe> recupererModeTransport(int page) {
        return modeTransportRepository.findAllByDeleted(Deletion.NO, PageRequest.of(page, 10))
                .map(c -> ModeTransportDtoListe.builder()
                        .id(c.getId())
                        .intitule(c.getIntitule())
                        .build()
                );
    }

    @Transactional(readOnly = true)
    public List<ModeTransportDtoListe> recupererModeTransportSansPagination() {
        return modeTransportRepository.findAllByDeleted(Deletion.NO)
                .stream()
                .map(c -> ModeTransportDtoListe.builder()
                        .id(c.getId())
                        .intitule(c.getIntitule())
                        .build()
                )
                .toList();
    }

    public boolean supprimerModeTransport(Long id) {
        return modeTransportRepository.findFirstByIdAndDeleted(id, Deletion.NO)
                .map(modeTransport -> {
                    modeTransport.setDeleted(Deletion.YES);
                    modeTransportRepository.save(modeTransport);
                    return true;
                })
                .orElse(false);
    }

    @Transactional
    public ResponsableVendeur creerResponsableVendeur(ResponsableVendeurDto dto) {
        ResponsableVendeur enregistrer = new ResponsableVendeur();
        enregistrer.setLibelle(dto.libelle());
        return responsableVendeurRepository.save(enregistrer);
    }

    @Transactional
    public ResponsableVendeurDto mettreAJourResponsableVendeur(Long id, ResponsableVendeurDto dto) {
        ResponsableVendeur entity = responsableVendeurRepository.findFirstByIdAndDeleted(id, Deletion.NO)
                .orElseThrow(() -> new NoSuchElementException("Le responsable vendeur est introuvable"));
        entity.setLibelle(dto.libelle());
        ResponsableVendeur updated = responsableVendeurRepository.save(entity);
        return new ResponsableVendeurDto(updated.getId(), updated.getLibelle());
    }

    @Transactional(readOnly = true)
    public Page<ResponsableVendeurDtoListe> recupererResponsableVendeur(int page) {
        return responsableVendeurRepository.findAllByDeleted(Deletion.NO, PageRequest.of(page, 10))
                .map(c -> ResponsableVendeurDtoListe.builder()
                        .id(c.getId())
                        .libelle(c.getLibelle())
                        .build()
                );
    }

    @Transactional(readOnly = true)
    public List<ResponsableVendeurDtoListe> recupererResponsableVendeurAvecPagnination() {
        return responsableVendeurRepository.findAllByDeleted(Deletion.NO)
                .stream()
                .map(c -> ResponsableVendeurDtoListe.builder()
                        .id(c.getId())
                        .libelle(c.getLibelle())
                        .build()
                )
                .toList();

    }

    public boolean supprimerResponsableVendeur(Long id) {
        return responsableVendeurRepository.findFirstByIdAndDeleted(id, Deletion.NO)
                .map(responsableVendeur -> {
                    responsableVendeur.setDeleted(Deletion.YES);
                    responsableVendeurRepository.save(responsableVendeur);
                    return true;
                })
                .orElse(false);
    }

}
