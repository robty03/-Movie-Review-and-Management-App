package com.proiect.proiect.service;

import com.proiect.proiect.model.Film;
import com.proiect.proiect.model.Recenzie;
import com.proiect.proiect.model.RecenzieStatus;
import com.proiect.proiect.model.Utilizator;
import com.proiect.proiect.repository.FilmRepository;
import com.proiect.proiect.repository.RecenzieRepository;
import com.proiect.proiect.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Clasa pentru RecenzieService
 * @author Vile Robert-Andrei
 * @version 12 Ianuarie 2025
 */

@Service
public class RecenzieService {

    private final RecenzieRepository recenzieRepository;
    private final FilmRepository filmRepository;
    private final UserRepository userRepository;

    public RecenzieService(RecenzieRepository recenzieRepository, FilmRepository filmRepository, UserRepository userRepository) {
        this.recenzieRepository = recenzieRepository;
        this.filmRepository = filmRepository;
        this.userRepository = userRepository;
    }

    public List<Recenzie> getReviewsByUser(String username) {
        Utilizator user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Utilizatorul nu a fost găsit"));
        return recenzieRepository.findAllByUtilizator(user);
    }

    public List<Recenzie> getAllRecenzii() {
        return recenzieRepository.findAll();
    }

    public void deleteRecenzie(Long id) {
        if (recenzieRepository.existsById(id)) {
            recenzieRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Recenzia cu ID-ul " + id + " nu există.");
        }
    }

    public void deleteRecenzie(String username, Long recenzieId) {
        Recenzie recenzie = recenzieRepository.findById(recenzieId)
                .orElseThrow(() -> new IllegalArgumentException("Recenzia nu a fost găsită"));
        if (!recenzie.getUtilizator().getUsername().equals(username)) {
            throw new SecurityException("Nu ai permisiunea să ștergi această recenzie");
        }
        recenzieRepository.delete(recenzie);
    }

    public Recenzie getRecenzieById(Long recenzieId) {
        return recenzieRepository.findById(recenzieId)
                .orElseThrow(() -> new IllegalArgumentException("Recenzia nu a fost găsită"));
    }

    public void addRecenzie(String username, String text, int rating, Long filmId) {
        Film film = filmRepository.findById(filmId).orElseThrow();
        Utilizator utilizator = userRepository.findByUsername(username).orElseThrow();
        Recenzie recenzie = new Recenzie(text, rating, film, utilizator);
        recenzie.setStatus(RecenzieStatus.IN_CURS_DE_APROBARE); // Set initial status
        recenzieRepository.save(recenzie);
    }


    public void updateRecenzie(String username, Long recenzieId, String text, int rating) {
        Recenzie recenzie = recenzieRepository.findById(recenzieId)
                .orElseThrow(() -> new IllegalArgumentException("Recenzia nu a fost găsită"));
        if (!recenzie.getUtilizator().getUsername().equals(username)) {
            throw new SecurityException("Nu ai permisiunea să modifici această recenzie");
        }
        recenzie.setText(text);
        recenzie.setRating(rating);
        recenzieRepository.save(recenzie);
    }

    public void saveRecenzie(Recenzie recenzie) {
        recenzieRepository.save(recenzie);
    }


}
