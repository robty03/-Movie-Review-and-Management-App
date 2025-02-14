package com.proiect.proiect.controller;

import com.proiect.proiect.dto.FilmRatingDTO;
import com.proiect.proiect.dto.GenrePopularityDTO;
import com.proiect.proiect.model.Favorite;
import com.proiect.proiect.model.Film;
import com.proiect.proiect.model.Recenzie;
import com.proiect.proiect.model.Utilizator;
import com.proiect.proiect.repository.UserRepository;
import com.proiect.proiect.service.FavoriteService;
import com.proiect.proiect.service.FilmService;
import com.proiect.proiect.service.RecenzieService;
import com.proiect.proiect.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

/**
 * Clasa pentru UtilizatorController
 * @author Vile Robert-Andrei
 * @version 12 Ianuarie 2025
 */

@Controller
public class UtilizatorController {

    private final FavoriteService favoriteService;
    private final UserService userService;
    private final FilmService filmService;
    private final RecenzieService recenzieService;
    private final UserRepository userRepository;

    public UtilizatorController(FavoriteService favoriteService, UserService userService, RecenzieService recenzieService, FilmService filmService,UserRepository userRepository) {
        this.favoriteService = favoriteService;
        this.userService = userService;
        this.recenzieService = recenzieService;
        this.filmService = filmService;
        this.userRepository = userRepository;
    }

    @GetMapping("/user/favorite")
    public String getFavoritePage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Utilizator user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Utilizatorul nu a fost găsit"));

        List<Film> favoriteMovies = favoriteService.getFavoriteFilms(user);
        List<Film> availableMovies = filmService.getAllFilms();
        model.addAttribute("favoriteMovies", favoriteMovies);
        model.addAttribute("availableMovies", availableMovies);
        return "user/favorite";
    }


    @GetMapping("/user/recenzii-mele")
    public String getMyReviewsPage(Model model, Authentication authentication) {
        String username = authentication.getName();
        List<Recenzie> myReviews = recenzieService.getReviewsByUser(username);
        model.addAttribute("recenzii", myReviews);
        return "user/recenzii-mele";
    }


    @GetMapping("/user/add-recenzie")
    public String showAddRecenzieForm(Model model) {
        List<Film> films = filmService.getAllFilms();
        model.addAttribute("filme", films);
        return "user/addRecenzie";
    }


    @PostMapping("/user/add-recenzie")
    public String addRecenzie(@RequestParam String text, @RequestParam int rating, @RequestParam Long filmId, Authentication authentication) {
        String username = authentication.getName();
        recenzieService.addRecenzie(username, text, rating, filmId);
        return "redirect:/user/recenzii-mele";
    }

    @PostMapping("/user/delete-recenzie")
    public String deleteRecenzie(@RequestParam Long id, Authentication authentication) {
        String username = authentication.getName();
        recenzieService.deleteRecenzie(username, id);
        return "redirect:/user/recenzii-mele";
    }

    @GetMapping("/user/edit-recenzie")
    public String showEditRecenzieForm(@RequestParam Long id, Model model) {
        Recenzie recenzie = recenzieService.getRecenzieById(id);
        model.addAttribute("recenzie", recenzie);
        model.addAttribute("filme", filmService.getAllFilms());
        return "user/editRecenzie";
    }

    @PostMapping("/user/edit-recenzie")
    public String editRecenzie(@RequestParam Long id, @RequestParam String text, @RequestParam int rating, Authentication authentication) {
        String username = authentication.getName();
        recenzieService.updateRecenzie(username, id, text, rating);
        return "redirect:/user/recenzii-mele";
    }

    @PostMapping("/user/add-favorite")
    public ModelAndView addFavorite(@RequestParam Long filmId, Authentication authentication) {
        String username = authentication.getName();
        favoriteService.addFavorite(username, filmId);
        return new ModelAndView("redirect:/user/favorite");
    }

    @PostMapping("/user/remove-favorite")
    public String removeFavorite(@RequestParam Long favoriteId, Authentication authentication) {
        String username = authentication.getName();
        favoriteService.removeFavorite(username, favoriteId);
        return "redirect:/user/favorite";
    }

    @GetMapping("/user/home")
    public String userHome(Model model, Authentication authentication) {
        String username = authentication.getName();
        model.addAttribute("username", username);
        return "user/home";
    }


    @GetMapping("/user/filme-populare")
    public String getUserPopularFilms(Model model) {
        List<FilmRatingDTO> popularFilms = filmService.getPopularFilms();
        model.addAttribute("popularFilms", popularFilms);
        return "user/top-filme-evaluari";
    }


    @GetMapping("/user/filme-genuri")
    public String getUserPopularGenres(Model model) {
        List<GenrePopularityDTO> genres = filmService.getPopularGenres();
        model.addAttribute("genres", genres);
        return "user/genuri-populare";
    }

}
