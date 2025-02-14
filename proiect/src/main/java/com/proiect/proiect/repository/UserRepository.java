package com.proiect.proiect.repository;

import com.proiect.proiect.model.Rol;
import com.proiect.proiect.model.Utilizator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Clasa pentru UserRepository
 * @author Vile Robert-Andrei
 * @version 12 Ianuarie 2025
 */

public interface UserRepository extends JpaRepository<Utilizator, Long> {
    Optional<Utilizator> findByUsername(String username);
    List<Utilizator> findByRolNot(Rol rol);
    boolean existsByUsername(String username);
    Optional<Utilizator> findByEmail(String email);
}
