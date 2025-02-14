package com.proiect.proiect.model;

import jakarta.persistence.*;

/**
 * Clasa pentru Favorite
 * @author Vile Robert-Andrei
 * @version 12 Ianuarie 2025
 */

@Entity
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Utilizator user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "film_id", nullable = false)
    private Film film;


    public Favorite() {
    }


    public Favorite(Film film, Utilizator user) {
        this.film = film;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Utilizator getUser() {
        return user;
    }

    public void setUser(Utilizator user) {
        this.user = user;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }
}


