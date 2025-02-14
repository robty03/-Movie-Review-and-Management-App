package com.proiect.proiect.controller;

import com.proiect.proiect.dto.FilmRatingDTO;
import com.proiect.proiect.dto.GenrePopularityDTO;
import com.proiect.proiect.model.Film;
import com.proiect.proiect.model.Recenzie;
import com.proiect.proiect.model.RecenzieStatus;
import com.proiect.proiect.model.Utilizator;
import com.proiect.proiect.service.FilmService;
import com.proiect.proiect.service.RecenzieService;
import com.proiect.proiect.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;

/**
 * Clasa pentru AdminController
 * @author Vile Robert-Andrei
 * @version 12 Ianuarie 2025
 */

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final FilmService filmService;
    private final RecenzieService recenzieService;

    public AdminController(UserService userService, FilmService filmService, RecenzieService recenzieService) {
        this.userService = userService;
        this.filmService = filmService;
        this.recenzieService = recenzieService;
    }


    @GetMapping("/home")
    public String adminHome(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        model.addAttribute("username", username);
        return "admin/home";
    }

    @GetMapping("/utilizatori")
    public String getAllUsers(Model model) {
        List<Utilizator> utilizatori = userService.getAllUsers();
        model.addAttribute("utilizatori", utilizatori);
        return "admin/utilizatori";
    }

    @GetMapping("/utilizatori/sterge/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/admin/utilizatori";
    }

    @GetMapping("/filme")
    public String getAllFilms(Model model) {
        List<Film> filme = filmService.getAllFilms();
        model.addAttribute("filme", filme);
        return "admin/filme";
    }

    @GetMapping("/filme/sterge/{id}")
    public String deleteFilm(@PathVariable Long id) {
        filmService.deleteFilm(id);
        return "redirect:/admin/filme";
    }

    @GetMapping("/add-film")
    public String showAddFilmForm(Model model) {
        model.addAttribute("film", new Film());
        return "admin/add-film";
    }

    @PostMapping("/add-film")
    public String addFilm(@ModelAttribute Film film) {
        filmService.addFilm(film);
        return "redirect:/admin/filme";
    }

    @GetMapping("/filme-populare")
    public String getPopularFilmsForAdmin(Model model) {
        List<FilmRatingDTO> popularFilms = filmService.getPopularFilms();
        model.addAttribute("popularFilms", popularFilms);
        return "admin/top-filme-evaluari";
    }

    @GetMapping("/genuri")
    public String getPopularGenres(Model model) {
        List<GenrePopularityDTO> genres = filmService.getPopularGenres();
        model.addAttribute("genres", genres);
        return "admin/genuri-populare";
    }


    @GetMapping("/recenzii")
    public String getRecenzii(Model model) {
        List<Recenzie> recenzii = recenzieService.getAllRecenzii();
        model.addAttribute("recenzii", recenzii);
        return "admin/recenzii"; // Către fișierul HTML
    }


    @GetMapping("/recenzii/sterge/{id}")
    public String deleteRecenzie(@PathVariable Long id) {
        recenzieService.deleteRecenzie(id);
        return "redirect:/admin/recenzii";
    }


    @GetMapping("/recenzii/aproba/{id}")
    public String approveRecenzie(@PathVariable Long id) {
        Recenzie recenzie = recenzieService.getRecenzieById(id);
        recenzie.setStatus(RecenzieStatus.APROBATA);
        recenzieService.saveRecenzie(recenzie);
        return "redirect:/admin/recenzii";
    }


    @GetMapping("/recenzii/neaproba/{id}")
    public String disapproveRecenzie(@PathVariable Long id) {
        Recenzie recenzie = recenzieService.getRecenzieById(id);
        recenzie.setStatus(RecenzieStatus.NEAPROBATA);
        recenzieService.saveRecenzie(recenzie);
        return "redirect:/admin/recenzii";
    }


}
