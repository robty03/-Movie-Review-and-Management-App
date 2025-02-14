package com.proiect.proiect.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Clasa pentru Film
 * @author Vile Robert-Andrei
 * @version 12 Ianuarie 2025
 */

@Entity
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titlu;
    private String gen;
    private String descriere;

    @OneToMany(mappedBy = "film", cascade = CascadeType.ALL)
    private List<Recenzie> recenzii;

    @ManyToMany(mappedBy = "favoriteFilms")
    private List<Utilizator> utilizatori = new ArrayList<>();


    public Film() {
    }

    public Film(String titlu, String gen, String descriere) {
        this.titlu = titlu;
        this.gen = gen;
        this.descriere = descriere;
    }

    public List<Utilizator> getUtilizatori() {
        return utilizatori;
    }


    public Long getId() {
        return id;
    }

    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public String getGen() {
        return gen;
    }

    public void setGen(String gen) {
        this.gen = gen;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public List<Recenzie> getRecenzii() {
        return recenzii;
    }

    public void setRecenzii(List<Recenzie> recenzii) {
        this.recenzii = recenzii;
    }
}
