package com.proiect.proiect.repository;

import com.proiect.proiect.model.Favorite;
import com.proiect.proiect.model.Utilizator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Clasa pentru FavoriteRepository
 * @author Vile Robert-Andrei
 * @version 12 Ianuarie 2025
 */
    @Repository
    public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
        List<Favorite> findByUserId(Long utilizator);
    }



