package com.example.suivie_importBackend.repository;

import com.example.suivie_importBackend.models.Echeance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface EcheanceRepository extends JpaRepository<Echeance, Long> {

    List<Echeance> findAllByDeleted(Boolean deletion);

    Page<Echeance> findAllByDeleted(Boolean deletion, Pageable pageable);

    Optional<Echeance> findByIdAndDeleted(Long id, Boolean deletion);

    Optional<Echeance> findFirstByIdAndDeleted(Long id, Boolean deletion);
}
