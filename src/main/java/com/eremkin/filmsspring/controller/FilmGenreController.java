package com.eremkin.filmsspring.controller;

import com.eremkin.filmsspring.model.FilmGenre;
import com.eremkin.filmsspring.model.FilmGenreId;
import com.eremkin.filmsspring.repository.FilmGenreRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/film-genres")
public class FilmGenreController {

    private final FilmGenreRepository repository;

    public FilmGenreController(FilmGenreRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<FilmGenre> getAll() {
        return repository.findAll();
    }

    @GetMapping("/film/{filmId}")
    public List<FilmGenre> getByFilm(@PathVariable Long filmId) {
        return repository.findByFilmId(filmId);
    }

    @GetMapping("/genre/{genreId}")
    public List<FilmGenre> getByGenre(@PathVariable Long genreId) {
        return repository.findByGenreId(genreId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public FilmGenre create(@RequestBody FilmGenre fg) {
        return repository.save(fg);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestBody FilmGenreId id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
