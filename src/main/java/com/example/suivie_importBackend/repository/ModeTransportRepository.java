package com.example.suivie_importBackend.repository;

import com.example.suivie_importBackend.models.ModeTransport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ModeTransportRepository extends JpaRepository<ModeTransport,Long> {

    Optional<ModeTransport> findFirstByIdAndDeleted(Long id, Boolean deletion);

    Page<ModeTransport> findAllByDeleted(Boolean deletion, Pageable pageable);

    List<ModeTransport> findAllByDeleted(Boolean deletion);
}
