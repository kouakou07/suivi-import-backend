package com.example.suivie_importBackend.repository;

import com.example.suivie_importBackend.models.TypeFournisseur;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface TypeFournisseurRepository extends JpaRepository<TypeFournisseur, Long> {

    Optional<TypeFournisseur> findByIdAndDeleted(Long id, Boolean deleted);

    Optional<TypeFournisseur> findFirstByIdAndDeleted(Long id, Boolean deleted);

    List<TypeFournisseur> findAllByDeleted(Boolean deleted);

    Page<TypeFournisseur> findAllByDeleted(Boolean deleted, Pageable pageable);
}
