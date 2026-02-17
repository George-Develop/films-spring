package com.eremkin.filmsspring.controller;

import com.eremkin.filmsspring.model.Genre;
import com.eremkin.filmsspring.repository.GenreRepository;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/genres")
public class GenresController {

    private final GenreRepository genreRepository;

    public GenresController(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @GetMapping
    public String listGenres(Model model) {
        List<Genre> genres = genreRepository.findAll();
        model.addAttribute("genres", genres);
        model.addAttribute("genre", new Genre());
        return "genres";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    public String addGenre(@Valid @ModelAttribute("genre") Genre genre,
                           BindingResult bindingResult,
                           Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("genres", genreRepository.findAll());
            return "genres"; // показываем ошибки
        }
        genreRepository.save(genre);
        return "redirect:/genres";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/delete/{id}")
    public String deleteGenre(@PathVariable Long id) {
        genreRepository.deleteById(id);
        return "redirect:/genres";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/edit/{id}")
    public String editGenreForm(@PathVariable Long id, Model model) {
        Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Неверный ID жанра: " + id));
        model.addAttribute("genre", genre);
        return "genre_edit";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/edit/{id}")
    public String editGenre(@PathVariable Long id,
                            @Valid @ModelAttribute("genre") Genre genre,
                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "genre_edit";
        }
        genre.setId(id);
        genreRepository.save(genre);
        return "redirect:/genres";
    }

}
