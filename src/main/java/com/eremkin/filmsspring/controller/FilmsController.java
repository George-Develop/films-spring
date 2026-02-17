package com.eremkin.filmsspring.controller;

import com.eremkin.filmsspring.model.Film;
import com.eremkin.filmsspring.model.Genre;
import com.eremkin.filmsspring.repository.FilmRepository;
import com.eremkin.filmsspring.repository.GenreRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/films")
public class FilmsController {

    private final FilmRepository filmRepository;
    private final GenreRepository genreRepository;

    public FilmsController(FilmRepository filmRepository, GenreRepository genreRepository) {
        this.filmRepository = filmRepository;
        this.genreRepository = genreRepository;
    }

    @GetMapping
    public String listFilms(Model model) {
        List<Film> films = filmRepository.findAll();
        List<Genre> genres = genreRepository.findAll();
        model.addAttribute("films", films);
        model.addAttribute("genres", genres);
        model.addAttribute("film", new Film());
        return "films";
    }

    @PostMapping("/add")
    public String addFilm(@Valid @ModelAttribute("film") Film film,
                          BindingResult bindingResult,
                          Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("films", filmRepository.findAll());
            return "films"; // Показываем ошибки
        }
        filmRepository.save(film);
        return "redirect:/films";
    }

    @PostMapping("/delete/{id}")
    public String deleteFilm(@PathVariable Long id) {
        filmRepository.deleteById(id);
        return "redirect:/films";
    }

    @GetMapping("/edit/{id}")
    public String editFilmForm(@PathVariable Long id, Model model) {
        Film film = filmRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Неверный ID фильма: " + id));
        model.addAttribute("film", film);
        return "film_edit";
    }

    @PostMapping("/edit/{id}")
    public String editFilm(@PathVariable Long id,
                           @Valid @ModelAttribute("film") Film film,
                           BindingResult bindingResult,
                           Model model) {
        if (bindingResult.hasErrors()) {
            return "film_edit";
        }
        film.setId(id);
        filmRepository.save(film);
        return "redirect:/films";
    }

}
