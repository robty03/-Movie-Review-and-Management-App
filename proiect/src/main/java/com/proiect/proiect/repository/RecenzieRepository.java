package com.proiect.proiect.repository;

import com.proiect.proiect.model.Recenzie;
import com.proiect.proiect.model.Utilizator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Clasa pentru RecenzieRepository
 * @author Vile Robert-Andrei
 * @version 12 Ianuarie 2025
 */
    public interface RecenzieRepository extends JpaRepository<Recenzie, Long> {
        List<Recenzie> findAllByUtilizator(Utilizator utilizator);
    }

