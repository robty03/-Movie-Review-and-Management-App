package com.proiect.proiect.model;

import jakarta.persistence.*;

/**
 * Clasa pentru Recenzie
 * @author Vile Robert-Andrei
 * @version 12 Ianuarie 2025
 */

@Entity
public class Recenzie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;
    private int rating;

    @ManyToOne
    @JoinColumn(name = "film_id")
    private Film film;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Utilizator utilizator;

    @Enumerated(EnumType.STRING)
    private RecenzieStatus status;

    public Recenzie() {
    }

    public Recenzie(String text, int rating, Film film, Utilizator utilizator) {
        this.text = text;
        this.rating = rating;
        this.film = film;
        this.utilizator = utilizator;
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Utilizator getUtilizator() {
        return utilizator;
    }

    public void setUtilizator(Utilizator utilizator) {
        this.utilizator = utilizator;
    }

    public RecenzieStatus getStatus() {
        return status;
    }

    public void setStatus(RecenzieStatus status) {
        this.status = status;
    }
}
