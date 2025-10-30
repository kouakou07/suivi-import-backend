package com.example.suivie_importBackend.controller;


import com.example.suivie_importBackend.dto.*;
import com.example.suivie_importBackend.models.*;
import com.example.suivie_importBackend.service.ParametreService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ParametreController {

    ParametreService parametreService;

    public ParametreController(ParametreService parametreService) {
        this.parametreService = parametreService;
    }

    @Secured({"ROLE_ADMIN", "ROLE_VALIDATEUR", "ROLE_USER"})
    @PostMapping("/pays/ajouter")
    public ResponseEntity<Pays> creerPays(@RequestBody Pays pays) {
        return parametreService.creerPays(pays);
    }

    @Secured({"ROLE_ADMIN", "ROLE_VALIDATEUR", "ROLE_USER"})
    @PutMapping("/pays/modifier")
    public ResponseEntity<PaysDto> modifierPays(@RequestBody PaysDto paysDto) {
        return ResponseEntity.ok(parametreService.mettreAJourPays(paysDto));
    }

    @Secured({"ROLE_ADMIN", "ROLE_VALIDATEUR", "ROLE_USER"})
    @DeleteMapping("/pays/supprimer/{id}")
    public ResponseEntity<Void> supprimerPays(@PathVariable Long id) {
        boolean deleted = parametreService.supprimerPays(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @Secured({"ROLE_ADMIN", "ROLE_VALIDATEUR", "ROLE_USER"})
    @GetMapping("/pays/liste/{page}")
    public ResponseEntity<Page<PaysDtoList>> recupererPays(
            @RequestParam(defaultValue = "0") int page
    ) {

        Page<PaysDtoList> paysPage = parametreService.recupererPaysAvecPagination(page);
        return ResponseEntity.ok(paysPage);
    }

    @Secured({"ROLE_ADMIN", "ROLE_VALIDATEUR", "ROLE_USER"})
    @GetMapping("/pays/liste")
    public ResponseEntity<List<PaysDtoList>> recupererPays(
    ) {
        List<PaysDtoList> paysPage = parametreService.recupererPays();
        return ResponseEntity.ok(paysPage);
    }

    @Secured({"ROLE_ADMIN", "ROLE_VALIDATEUR", "ROLE_USER"})
    @PostMapping("/modes-envoie/ajouter")
    public ResponseEntity<ModeEnvoi> creerModeEnvoi(@RequestBody ModeEnvoiDto modeEnvoi) {
        ModeEnvoi enregistreModeEnvoi = parametreService.creerModeEnvoi(modeEnvoi);
        return ResponseEntity.status(HttpStatus.CREATED).body(enregistreModeEnvoi);
    }

    @Secured({"ROLE_ADMIN", "ROLE_VALIDATEUR", "ROLE_USER"})
    @PostMapping("/modes-envoie/editer/{id}")
    public ResponseEntity<ModeEnvoiDto> modifierModeEnvoi(
            @PathVariable Long id,
            @RequestBody ModeEnvoiDto modeEnvoiDto
    ) {
        ModeEnvoiDto modeMisAJour = parametreService.mettreAJourModeEnvoi(id, modeEnvoiDto);
        return ResponseEntity.ok(modeMisAJour);
    }

    @Secured({"ROLE_ADMIN", "ROLE_VALIDATEUR", "ROLE_USER"})
    @GetMapping("/modes-envoie/liste/{page}")
    public ResponseEntity<Page<ModeEnvoiListDto>> recupererModeEnvoi(
            @RequestParam(defaultValue = "0") int page
    ) {
        Page<ModeEnvoiListDto> modePage = parametreService.recupererModeEnvoi(page);
        return ResponseEntity.ok(modePage);
    }

    @Secured({"ROLE_ADMIN", "ROLE_VALIDATEUR", "ROLE_USER"})
    @PostMapping("/modes-envoie/supprimer/{id}")
    public ResponseEntity<Void> supprimerModeEnvoi(@PathVariable Long id) {
        boolean deleted = parametreService.supprimerModeEnvoi(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @Secured({"ROLE_ADMIN", "ROLE_VALIDATEUR", "ROLE_USER"})
    @PostMapping("/devises/ajouter")
    public ResponseEntity<Devise> creerDevise(@RequestBody DeviseDto devise) {
        Devise enregistreDevise = parametreService.creerDevise(devise);
        return ResponseEntity.status(HttpStatus.CREATED).body(enregistreDevise);
    }

    @Secured({"ROLE_ADMIN", "ROLE_VALIDATEUR", "ROLE_USER"})
    @PostMapping("/devises/editer/{id}")
    public ResponseEntity<DeviseDto> modifierDevise(@RequestBody DeviseDto deviseDto) {
        DeviseDto deviseMisAJour = parametreService.mettreAJourDevise(deviseDto);
        return ResponseEntity.ok(deviseMisAJour);
    }

    @Secured({"ROLE_ADMIN", "ROLE_VALIDATEUR", "ROLE_USER"})
    @GetMapping("/devises/liste/{page}")
    public ResponseEntity<Page<DeviseDtoList>> recupererDevise(
            @RequestParam(defaultValue = "0") int page
    ) {
        Page<DeviseDtoList> devisePage = parametreService.recupererDeviseAvecPagination(page);
        return ResponseEntity.ok(devisePage);
    }

    @Secured({"ROLE_ADMIN", "ROLE_VALIDATEUR", "ROLE_USER"})
    @GetMapping("/devises/liste")
    public ResponseEntity<List<DeviseDtoList>> recupererDevise(
    ) {
        List<DeviseDtoList> devisePage = parametreService.recupererDevise();
        return ResponseEntity.ok(devisePage);
    }

    @Secured({"ROLE_ADMIN", "ROLE_VALIDATEUR", "ROLE_USER"})
    @PostMapping("/devises/supprimer/{id}")
    public ResponseEntity<Void> supprimerDevise(@PathVariable Long id) {
        boolean deleted = parametreService.supprimerDevise(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @Secured({"ROLE_ADMIN", "ROLE_VALIDATEUR", "ROLE_USER"})
    @PostMapping("/familles-centrales/ajouter")
    public ResponseEntity<FamilleCentrale> creerFamilleCentrale(@RequestBody FamilleCentrale familleCentrale) {
        FamilleCentrale enregistreFamille1 = parametreService.creerFamilleCentrale(familleCentrale);
        return ResponseEntity.status(HttpStatus.CREATED).body(enregistreFamille1);
    }

    @Secured({"ROLE_ADMIN", "ROLE_VALIDATEUR", "ROLE_USER"})
    @PostMapping("/familles-centrales/editer/{id}")
    public ResponseEntity<FamilleCentraleDto> modifierFamilleCentrale(@PathVariable Long id, @RequestBody FamilleCentraleDto familleCentraleDto) {
        FamilleCentraleDto familleMisAJour = parametreService.mettreAJourFamilleCentrale(id,familleCentraleDto);
        return ResponseEntity.ok(familleMisAJour);
    }

    @Secured({"ROLE_ADMIN", "ROLE_VALIDATEUR", "ROLE_USER"})
    @GetMapping("/familles-centrales/liste/{page}")
    public ResponseEntity<Page<FamilleCentraleDtoList>> recupererFamilleCentrale(
            @RequestParam(defaultValue = "0") int page
    ) {
        Page<FamilleCentraleDtoList> familleCentralePage = parametreService.recupererFamilleCentrale(page);
        return ResponseEntity.ok(familleCentralePage);
    }

    @Secured({"ROLE_ADMIN", "ROLE_VALIDATEUR", "ROLE_USER"})
    @PostMapping("/familles-centrales/supprimer/{id}")
    public ResponseEntity<Void> supprimerFamilleCentrale(@PathVariable Long id) {
        boolean deleted = parametreService.supprimerFamilleCentrale(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @Secured({"ROLE_ADMIN", "ROLE_VALIDATEUR", "ROLE_USER"})
    @PostMapping("/type-fournisseurs/ajouter")
    public ResponseEntity<TypeFournisseur> creerTypeFournisseur(@RequestBody TypeFournisseurDto typeFournisseur) {
        TypeFournisseur enregistreTypeFournisseur = parametreService.creerTypeFournisseur(typeFournisseur);
        return ResponseEntity.status(HttpStatus.CREATED).body(enregistreTypeFournisseur);
    }

    @Secured({"ROLE_ADMIN", "ROLE_VALIDATEUR", "ROLE_USER"})
    @PostMapping("/type-fournisseurs/editer/{id}")
    public ResponseEntity<TypeFournisseurDto> modifierTypeFournisseur(@PathVariable Long id, @RequestBody TypeFournisseurDto typeFournisseurDto) {
        TypeFournisseurDto typeFournisseurMisAJour = parametreService.mettreAJourTypeFournisseur(id, typeFournisseurDto);
        return ResponseEntity.ok(typeFournisseurMisAJour);
    }

    @Secured({"ROLE_ADMIN", "ROLE_VALIDATEUR", "ROLE_USER"})
    @GetMapping("/type-fournisseurs/liste/{page}")
    public ResponseEntity<Page<TypeFournisseurDtoListe>> recupererTypeFournisseur(
            @PathVariable int page
    ) {
        Page<TypeFournisseurDtoListe> typeFournisseurPage = parametreService.recupererTypeFournisseur(page);
        return ResponseEntity.ok(typeFournisseurPage);
    }

    @Secured({"ROLE_ADMIN", "ROLE_VALIDATEUR", "ROLE_USER"})
    @PostMapping("/type-fournisseurs/supprimer/{id}")
    public ResponseEntity<Void> supprimerTypeFournisseur(@PathVariable Long id) {
        boolean deleted = parametreService.supprimerTypeFournisseur(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @Secured({"ROLE_ADMIN", "ROLE_VALIDATEUR", "ROLE_USER"})
    @PostMapping("/departements/ajouter")
    public ResponseEntity<Departement> creerDepartement(@RequestBody DepartementDto departementDto) {
        Departement enregistreDepartement = parametreService.creerDepartement(departementDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(enregistreDepartement);
    }

    @Secured({"ROLE_ADMIN", "ROLE_VALIDATEUR", "ROLE_USER"})
    @PostMapping("/departements/editer/{id}")
    public ResponseEntity<DepartementDto> modifierDepartement(@PathVariable Long id, @RequestBody DepartementDto departementDto) {
        DepartementDto mettreAJourDepartement = parametreService.mettreAJourDepartement(id, departementDto);
        return ResponseEntity.ok(mettreAJourDepartement);
    }

    @Secured({"ROLE_ADMIN", "ROLE_VALIDATEUR", "ROLE_USER"})
    @GetMapping("/departements/liste/{page}")
    public ResponseEntity<Page<DepartementDtoListe>> recupererDepartement(
            @PathVariable int page
    ) {
        Page<DepartementDtoListe> departementDtoListes = parametreService.recupererDepartement(page);
        return ResponseEntity.ok(departementDtoListes);
    }

    @Secured({"ROLE_ADMIN", "ROLE_VALIDATEUR", "ROLE_USER"})
    @PostMapping("/departements/supprimer/{id}")
    public ResponseEntity<Void> supprimerDepartement(@PathVariable Long id) {
        boolean deleted = parametreService.supprimerDepartement(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @Secured({"ROLE_ADMIN", "ROLE_VALIDATEUR", "ROLE_USER"})
    @PostMapping("/mode-paiements/ajouter")
    public ResponseEntity<ModePaiement> creerModePaiement(@RequestBody ModePaiementDto modePaiementDto) {
        ModePaiement enregistreModePaiement = parametreService.creerModePaiement(modePaiementDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(enregistreModePaiement);
    }

    @Secured({"ROLE_ADMIN", "ROLE_VALIDATEUR", "ROLE_USER"})
    @PostMapping("/mode-paiements/editer/{id}")
    public ResponseEntity<ModePaiementDto> modifierModePaiement(@PathVariable Long id, @RequestBody ModePaiementDto modePaiementDto) {
        ModePaiementDto mettreAJourModePaiement = parametreService.mettreAJourModePaiement(id, modePaiementDto);
        return ResponseEntity.ok(mettreAJourModePaiement);
    }

    @Secured({"ROLE_ADMIN", "ROLE_VALIDATEUR", "ROLE_USER"})
    @GetMapping("/mode-paiements/liste/{page}")
    public ResponseEntity<Page<ModePaiementDtoListe>> recupererModePaiement(
            @PathVariable int page
    ) {
        Page<ModePaiementDtoListe> modePaiementDtoListes = parametreService.recupererModePaiement(page);
        return ResponseEntity.ok(modePaiementDtoListes);
    }

    @Secured({"ROLE_ADMIN", "ROLE_VALIDATEUR", "ROLE_USER"})
    @GetMapping("/mode-paiements/liste")
    public ResponseEntity<List<ModePaiementDtoListe>> recupererModePaiement(
    ) {
        return ResponseEntity.ok(parametreService.recupererModePaiementSanspagination());
    }

    @Secured({"ROLE_ADMIN", "ROLE_VALIDATEUR", "ROLE_USER"})
    @PostMapping("/mode-paiements/supprimer/{id}")
    public ResponseEntity<Void> supprimerModePaiement(@PathVariable Long id) {
        boolean deleted = parametreService.supprimerModePaiement(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @Secured({"ROLE_ADMIN", "ROLE_VALIDATEUR", "ROLE_USER"})
    @PostMapping("/modes-transports/ajouter")
    public ResponseEntity<ModeTransport> creerModeTransport(@RequestBody ModeTransportDto modeTransportDto) {
        ModeTransport enregistreModeTransport = parametreService.creerModeTransport(modeTransportDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(enregistreModeTransport);
    }

    @Secured({"ROLE_ADMIN", "ROLE_VALIDATEUR", "ROLE_USER"})
    @PostMapping("/modes-transports/editer/{id}")
    public ResponseEntity<ModeTransportDto> modifierModeTransport(@PathVariable Long id, @RequestBody ModeTransportDto modeTransportDto) {
        ModeTransportDto mettreAJourModeTransport = parametreService.mettreAJourModeTransport(id, modeTransportDto);
        return ResponseEntity.ok(mettreAJourModeTransport);
    }

    @Secured({"ROLE_ADMIN", "ROLE_VALIDATEUR", "ROLE_USER"})
    @GetMapping("/modes-transports/liste/{page}")
    public ResponseEntity<Page<ModeTransportDtoListe>> recupererModeTransport(
            @PathVariable int page
    ) {
        Page<ModeTransportDtoListe> modeTransportDtoListes = parametreService.recupererModeTransport(page);
        return ResponseEntity.ok(modeTransportDtoListes);
    }

    @Secured({"ROLE_ADMIN", "ROLE_VALIDATEUR", "ROLE_USER"})
    @GetMapping("/modes-transports/liste")
    public ResponseEntity<List<ModeTransportDtoListe>> recupererListeSansPagination() {
        return ResponseEntity.ok(parametreService.recupererModeTransportSansPagination());
    }

    @Secured({"ROLE_ADMIN", "ROLE_VALIDATEUR", "ROLE_USER"})
    @PostMapping("/modes-transports/supprimer/{id}")
    public ResponseEntity<Void> supprimerModeTransport(@PathVariable Long id) {
        boolean deleted = parametreService.supprimerModeTransport(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @Secured({"ROLE_ADMIN", "ROLE_VALIDATEUR", "ROLE_USER"})
    @PostMapping("/responsabilites-vendeurs/ajouter")
    public ResponseEntity<ResponsableVendeur> creerResponsableVendeur(@RequestBody ResponsableVendeurDto dto) {
        ResponsableVendeur enregistre = parametreService.creerResponsableVendeur(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(enregistre);
    }

    @Secured({"ROLE_ADMIN", "ROLE_VALIDATEUR", "ROLE_USER"})
    @PostMapping("/responsabilites-vendeurs/editer/{id}")
    public ResponseEntity<ResponsableVendeurDto> modifierResponsableVendeur(@PathVariable Long id, @RequestBody ResponsableVendeurDto dto) {
        ResponsableVendeurDto mettreAJour = parametreService.mettreAJourResponsableVendeur(id, dto);
        return ResponseEntity.ok(mettreAJour);
    }

    @Secured({"ROLE_ADMIN", "ROLE_VALIDATEUR", "ROLE_USER"})
    @GetMapping("/responsabilites-vendeurs/liste/{page}")
    public ResponseEntity<Page<ResponsableVendeurDtoListe>> recupererResponsableVendeur(@PathVariable int page) {
        Page<ResponsableVendeurDtoListe> liste = parametreService.recupererResponsableVendeur(page);
        return ResponseEntity.ok(liste);
    }

    @Secured({"ROLE_ADMIN", "ROLE_VALIDATEUR", "ROLE_USER"})
    @GetMapping("/responsabilites-vendeurs/liste")
    public ResponseEntity<List<ResponsableVendeurDtoListe>> recupererResponsableVendeur() {
       return ResponseEntity.ok(parametreService.recupererResponsableVendeurAvecPagnination());

    }

    @Secured({"ROLE_ADMIN", "ROLE_VALIDATEUR", "ROLE_USER"})
    @PostMapping("/responsabilites-vendeurs/supprimer/{id}")
    public ResponseEntity<Void> supprimerResponsableVendeur(@PathVariable Long id) {
        boolean deleted = parametreService.supprimerResponsableVendeur(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @Secured({"ROLE_ADMIN", "ROLE_VALIDATEUR", "ROLE_USER"})
    @PostMapping("/unites-ventes/ajouter")
    public ResponseEntity<UniteVente> creerUniteVente(@RequestBody UniteVenteDto dto) {
        UniteVente enregistre = parametreService.creerUniteVente(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(enregistre);
    }

    @Secured({"ROLE_ADMIN", "ROLE_VALIDATEUR", "ROLE_USER"})
    @PostMapping("/unites-ventes/editer/{id}")
    public ResponseEntity<UniteVenteDto> modifierUniteVente(@PathVariable Long id, @RequestBody UniteVenteDto dto) {
        UniteVenteDto mettreAJour = parametreService.mettreAJourUniteVente(id, dto);
        return ResponseEntity.ok(mettreAJour);
    }

    @Secured({"ROLE_ADMIN", "ROLE_VALIDATEUR", "ROLE_USER"})
    @GetMapping("/unites-ventes/liste/{page}")
    public ResponseEntity<Page<UniteVenteDtoListe>> recupererUniteVente(@PathVariable int page) {
        Page<UniteVenteDtoListe> liste = parametreService.recupererUniteVente(page);
        return ResponseEntity.ok(liste);
    }

    @Secured({"ROLE_ADMIN", "ROLE_VALIDATEUR", "ROLE_USER"})
    @GetMapping("/unites-ventes/liste")
    public ResponseEntity<List<UniteVenteDtoListe>> recupererUniteVente() {
        return ResponseEntity.ok(parametreService.recupererUniteVenteAvecPagination());
    }

    @Secured({"ROLE_ADMIN", "ROLE_VALIDATEUR", "ROLE_USER"})
    @PostMapping("/unites-ventes/supprimer/{id}")
    public ResponseEntity<Void> supprimerUniteVente(@PathVariable Long id) {
        boolean deleted = parametreService.supprimerUniteVente(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @Secured({"ROLE_ADMIN", "ROLE_VALIDATEUR", "ROLE_USER"})
    @PostMapping("/echeances/ajouter")
    public ResponseEntity<Echeance> creerEcheance(@RequestBody EcheanceDto dto) {
        Echeance enregistre = parametreService.creerEcheance(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(enregistre);
    }

    @Secured({"ROLE_ADMIN", "ROLE_VALIDATEUR", "ROLE_USER"})
    @PostMapping("/echeances/editer/{id}")
    public ResponseEntity<EcheanceDto> modifierEcheance(@PathVariable Long id, @RequestBody EcheanceDto dto) {
        EcheanceDto miseAJour = parametreService.mettreAJourEcheance(id, dto);
        return ResponseEntity.ok(miseAJour);
    }

    @Secured({"ROLE_ADMIN", "ROLE_VALIDATEUR", "ROLE_USER"})
    @GetMapping("/echeances/liste/{page}")
    public ResponseEntity<Page<EcheanceDtoListe>> recupererEcheances(@PathVariable int page) {
        Page<EcheanceDtoListe> liste = parametreService.recupererEcheances(page);
        return ResponseEntity.ok(liste);
    }

    @Secured({"ROLE_ADMIN", "ROLE_VALIDATEUR", "ROLE_USER"})
    @GetMapping("/echeances/liste")
    public ResponseEntity<List<EcheanceDtoListe>> recupererToutesLesEcheances() {
        return ResponseEntity.ok(parametreService.recupererToutesLesEcheances());
    }

    @Secured({"ROLE_ADMIN", "ROLE_VALIDATEUR", "ROLE_USER"})
    @PostMapping("/echeances/supprimer/{id}")
    public ResponseEntity<Void> supprimerEcheance(@PathVariable Long id) {
        boolean deleted = parametreService.supprimerEcheance(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
