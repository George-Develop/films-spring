package com.eremkin.filmsspring.controller;

import com.eremkin.filmsspring.model.FilmActor;
import com.eremkin.filmsspring.model.FilmActorId;
import com.eremkin.filmsspring.repository.FilmActorRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/film-actors")
public class FilmActorController {

    private final FilmActorRepository repository;

    public FilmActorController(FilmActorRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<FilmActor> getAll() {
        return repository.findAll();
    }

    @GetMapping("/film/{filmId}")
    public List<FilmActor> getByFilm(@PathVariable Long filmId) {
        return repository.findByFilmId(filmId);
    }

    @GetMapping("/actor/{actorId}")
    public List<FilmActor> getByActor(@PathVariable Long actorId) {
        return repository.findByActorId(actorId);
    }

    @PostMapping
    public FilmActor create(@RequestBody FilmActor filmActor) {
        return repository.save(filmActor);
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestBody FilmActorId id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
