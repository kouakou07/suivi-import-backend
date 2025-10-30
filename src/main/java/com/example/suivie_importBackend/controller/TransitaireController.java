package com.example.suivie_importBackend.controller;

import com.example.suivie_importBackend.dto.TransitaireDto;
import com.example.suivie_importBackend.service.TransitaireService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TransitaireController {

    private final TransitaireService transitaireService;

    public TransitaireController(TransitaireService transitaireService) {
        this.transitaireService = transitaireService;
    }

    @Secured({"ROLE_ADMIN", "ROLE_VALIDATEUR", "ROLE_USER"})
    @PostMapping("/transitaires/ajouter")
    public ResponseEntity<TransitaireDto> creerTransitaire(@RequestBody TransitaireDto dto) {
        TransitaireDto enregistre = transitaireService.creerTransitaire(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(enregistre);
    }

    @Secured({"ROLE_ADMIN", "ROLE_VALIDATEUR", "ROLE_USER"})
    @PostMapping("/transitaires/editer/{id}")
    public ResponseEntity<TransitaireDto> modifierTransitaire(@PathVariable Long id, @RequestBody TransitaireDto dto) {
        dto.setId(id);
        TransitaireDto mettreAJour = transitaireService.mettreAJourTransitaire(dto);
        return ResponseEntity.ok(mettreAJour);
    }

    @Secured({"ROLE_ADMIN", "ROLE_VALIDATEUR", "ROLE_USER"})
    @GetMapping("/transitaires/liste/{page}")
    public ResponseEntity<Page<TransitaireDto>> recupererTransitaire(@PathVariable int page) {
        Page<TransitaireDto> liste = transitaireService.recupererTousLesTransitairesAvecPagination(page);
        return ResponseEntity.ok(liste);
    }

    @Secured({"ROLE_ADMIN", "ROLE_VALIDATEUR", "ROLE_USER"})
    @GetMapping("/transitaires/liste")
    public ResponseEntity<List<TransitaireDto>> recupererTransitaire() {
        return ResponseEntity.ok(transitaireService.recupererTousLesTransitaires());
    }

    @Secured({"ROLE_ADMIN", "ROLE_VALIDATEUR", "ROLE_USER"})
    @PostMapping("/transitaires/supprimer/{id}")
    public ResponseEntity<String> supprimerTransitaire(@PathVariable Long id) {
        try {
            String message = transitaireService.supprimerTransitaire(id);
            return ResponseEntity.ok(message);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
