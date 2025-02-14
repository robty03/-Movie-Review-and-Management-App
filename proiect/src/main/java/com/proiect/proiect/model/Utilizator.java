package com.proiect.proiect.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Clasa pentru Utilizator
 * @author Vile Robert-Andrei
 * @version 12 Ianuarie 2025
 */

@Entity
public class Utilizator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String email;

    @Enumerated(EnumType.STRING)
    private Rol rol;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "utilizator_favorite",
            joinColumns = @JoinColumn(name = "utilizator_id"),
            inverseJoinColumns = @JoinColumn(name = "film_id")
    )
    private List<Film> favoriteFilms = new ArrayList<>();

    public List<Film> getFavoriteFilms() {
        return favoriteFilms;
    }

    public void setFavoriteFilms(List<Film> favoriteFilms) {
        this.favoriteFilms = favoriteFilms;
    }

    public void removeFavoriteFilm(Film film) {
        this.favoriteFilms.remove(film);
        film.getUtilizatori().remove(this);
    }



    public Utilizator() {
    }

    public Utilizator(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
}

