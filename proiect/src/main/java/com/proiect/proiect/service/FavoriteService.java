package com.proiect.proiect.service;

import com.proiect.proiect.model.Favorite;
import com.proiect.proiect.model.Film;
import com.proiect.proiect.model.Utilizator;
import com.proiect.proiect.repository.FavoriteRepository;
import com.proiect.proiect.repository.FilmRepository;
import com.proiect.proiect.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Clasa pentru FavoriteService
 * @author Vile Robert-Andrei
 * @version 12 Ianuarie 2025
 */

@Service
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final UserRepository userRepository;
    private final FilmRepository filmRepository;

    public FavoriteService(FavoriteRepository favoriteRepository, UserRepository userRepository, FilmRepository filmRepository) {
        this.favoriteRepository = favoriteRepository;
        this.userRepository = userRepository;
        this.filmRepository = filmRepository;
    }

    public List<Film> getFavoriteFilms(Utilizator user) {
        return user.getFavoriteFilms();
    }

    public void addFavorite(String username, Long filmId) {
        Utilizator user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        Film film = filmRepository.findById(filmId).orElseThrow(() -> new RuntimeException("Film not found"));
        if (!user.getFavoriteFilms().contains(film)) {
            user.getFavoriteFilms().add(film);
            userRepository.save(user);
        }
    }

    public void removeFavorite(String username, Long filmId) {
        Utilizator user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Utilizatorul nu a fost găsit"));
        Film film = filmRepository.findById(filmId)
                .orElseThrow(() -> new IllegalArgumentException("Filmul nu a fost găsit"));

        if (user.getFavoriteFilms().contains(film)) {
            user.getFavoriteFilms().remove(film);
            userRepository.save(user);
        }
    }

}
