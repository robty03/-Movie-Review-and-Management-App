package com.proiect.proiect.repository;

import com.proiect.proiect.dto.GenrePopularityDTO;
import com.proiect.proiect.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Clasa pentru FilmRepository
 * @author Vile Robert-Andrei
 * @version 12 Ianuarie 2025
 */

public interface FilmRepository extends JpaRepository<Film, Long> {

    @Query("SELECT f.gen AS gen, COUNT(f.id) AS numarFilme FROM Film f GROUP BY f.gen")
    List<Object[]> getPopularGenres();


    @Query("SELECT f.titlu, f.gen, COALESCE(AVG(r.rating), 0) AS avgRating " +
            "FROM Film f LEFT JOIN Recenzie r ON f.id = r.film.id " +
            "GROUP BY f.id, f.titlu, f.gen " +
            "ORDER BY avgRating DESC")
    List<Object[]> getPopularFilmsWithRatings();


}
