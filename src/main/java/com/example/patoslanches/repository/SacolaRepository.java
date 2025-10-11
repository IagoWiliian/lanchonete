package com.example.patoslanches.repository;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.patoslanches.model.Sacola;
import com.example.patoslanches.model.Usuario;

public interface SacolaRepository extends JpaRepository<Sacola, Long> {

    Optional<Sacola> findByClienteAndFinalizadaFalse(Usuario cliente);

    List<Sacola> findByClienteAndFinalizadaTrue(Usuario cliente);
}
