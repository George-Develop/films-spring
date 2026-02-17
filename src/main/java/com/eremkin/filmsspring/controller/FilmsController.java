package com.eremkin.filmsspring.controller;

import com.eremkin.filmsspring.model.Film;
import com.eremkin.filmsspring.model.Genre;
import com.eremkin.filmsspring.repository.ActorRepository;
import com.eremkin.filmsspring.repository.FilmRepository;
import com.eremkin.filmsspring.repository.GenreRepository;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;

@Controller
@RequestMapping("/films")
public class FilmsController {

    private final FilmRepository filmRepository;
    private final GenreRepository genreRepository;
    private final ActorRepository actorRepository;

    public FilmsController(FilmRepository filmRepository, GenreRepository genreRepository, ActorRepository actorRepository) {
        this.filmRepository = filmRepository;
        this.genreRepository = genreRepository;
        this.actorRepository = actorRepository;
    }

    @GetMapping
    public String listFilms(Model model) {
        model.addAttribute("films", filmRepository.findAll());
        model.addAttribute("film", new Film());
        model.addAttribute("allGenres", genreRepository.findAll());
        model.addAttribute("allActors", actorRepository.findAll());
        return "films";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    public String addFilm(@Valid @ModelAttribute("film") Film film,
                          BindingResult result,
                          @RequestParam(required = false) List<Long> genreIds,
                          @RequestParam(required = false) List<Long> actorIds,
                          Model model) {

        if (result.hasErrors()) {
            model.addAttribute("films", filmRepository.findAll());
            model.addAttribute("allGenres", genreRepository.findAll());
            model.addAttribute("allActors", actorRepository.findAll());
            return "films";
        }

        // Устанавливаем жанры
        if (genreIds != null) {
            film.setGenres(new HashSet<>(genreRepository.findAllById(genreIds)));
        }

        // Устанавливаем актёров
        if (actorIds != null) {
            film.setActors(new HashSet<>(actorRepository.findAllById(actorIds)));
        }

        filmRepository.save(film);
        return "redirect:/films";
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/delete/{id}")
    public String deleteFilm(@PathVariable Long id) {
        filmRepository.deleteById(id);
        return "redirect:/films";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/edit/{id}")
    public String editFilmForm(@PathVariable Long id, Model model) {

        Film film = filmRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid film ID"));

        model.addAttribute("film", film);
        model.addAttribute("allGenres", genreRepository.findAll());
        model.addAttribute("allActors", actorRepository.findAll());

        return "film_edit";
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/edit/{id}")
    public String updateFilm(@PathVariable Long id,
                             @Valid @ModelAttribute("film") Film film,
                             BindingResult result,
                             @RequestParam(required = false) List<Long> genreIds,
                             @RequestParam(required = false) List<Long> actorIds,
                             Model model) {

        if (result.hasErrors()) {
            model.addAttribute("allGenres", genreRepository.findAll());
            model.addAttribute("allActors", actorRepository.findAll());
            return "film_edit";
        }

        film.setId(id);

        if (genreIds != null) {
            film.setGenres(new HashSet<>(genreRepository.findAllById(genreIds)));
        } else {
            film.getGenres().clear();
        }

        if (actorIds != null) {
            film.setActors(new HashSet<>(actorRepository.findAllById(actorIds)));
        } else {
            film.getActors().clear();
        }

        filmRepository.save(film);
        return "redirect:/films";
    }


}
