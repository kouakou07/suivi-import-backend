package com.example.suivie_importBackend.repository;

import com.example.suivie_importBackend.models.Transitaire;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface TransitaireRepository extends JpaRepository<Transitaire, Long> {
    List<Transitaire> findAllByDeleted(Boolean deletion);

    Page<Transitaire> findAllByDeleted(Boolean deletion, Pageable page);

    Optional<Transitaire> findFirstByIdAndDeleted(Long id, Boolean deletion);

    Optional<Transitaire> findByIdAndDeleted(Long id, Boolean deletion);

    Page<Transitaire> findAllByDeletedOrderByCodeTransitaireAsc(Boolean deleted, Pageable pageable);

    List<Transitaire> findAllByIdInAndDeleted(List<Long> fournisseurIds, Boolean deletion);

    @Query("SELECT f FROM Transitaire f WHERE f.deleted = :deleted ORDER BY f.codeTransitaire ASC")
    Page<Transitaire> findAllByDeletedOrderByCodeTransitaireNum(@Param("deleted") Boolean deletion, Pageable pageable);
}
