package com.proiect.proiect.service;

import com.proiect.proiect.dto.FilmRatingDTO;
import com.proiect.proiect.dto.GenrePopularityDTO;
import com.proiect.proiect.model.Film;
import com.proiect.proiect.repository.FilmRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Clasa pentru FilmService
 * @author Vile Robert-Andrei
 * @version 12 Ianuarie 2025
 */

@Service
public class FilmService {

    private final FilmRepository filmRepository;

    public FilmService(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    public List<Film> getAllFilme() {
        return filmRepository.findAll();
    }

    public void addFilm(Film film) {
        filmRepository.save(film);
    }

    public List<FilmRatingDTO> getPopularFilms() {
        List<Object[]> results = filmRepository.getPopularFilmsWithRatings();
        return results.stream()
                .map(result -> new FilmRatingDTO(
                        (String) result[0],
                        (String) result[1],
                        result[2] != null ? ((Number) result[2]).doubleValue() : 0.0))
                .collect(Collectors.toList());
    }

    public List<Film> getAllFilms() {
        return filmRepository.findAll();
    }

    public void deleteFilm(Long id) {
        if (filmRepository.existsById(id)) {
            filmRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Film not found with id: " + id);
        }
    }

    public List<GenrePopularityDTO> getPopularGenres() {
        List<Object[]> results = filmRepository.getPopularGenres();
        return results.stream()
                .map(result -> new GenrePopularityDTO((String) result[0], ((Number) result[1]).intValue()))
                .collect(Collectors.toList());
    }
}
