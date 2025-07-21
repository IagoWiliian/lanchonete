package com.example.patoslanches.repository;

import com.example.patoslanches.model.Sacola;
import com.example.patoslanches.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SacolaRepository extends JpaRepository<Sacola, Long> {
    Optional<Sacola> findByClienteAndFinalizadaFalse(Usuario cliente);
}