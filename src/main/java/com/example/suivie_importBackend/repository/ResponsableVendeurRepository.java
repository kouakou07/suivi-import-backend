package com.example.suivie_importBackend.repository;


import com.example.suivie_importBackend.models.ResponsableVendeur;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ResponsableVendeurRepository extends JpaRepository<ResponsableVendeur,Long> {

    Optional<ResponsableVendeur> findFirstByIdAndDeleted(Long id, Boolean deletion);

    Page<ResponsableVendeur> findAllByDeleted(Boolean deletion, Pageable pageable);

    List<ResponsableVendeur> findAllByDeleted(Boolean deletion);
}
