package com.eremkin.filmsspring.repository;

import com.eremkin.filmsspring.model.FilmGenre;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class FilmGenreRepository {
    private final JdbcTemplate jdbc;

    public FilmGenreRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public int save(FilmGenre fg) {
        return jdbc.update(
                "INSERT INTO film_genres (film_id, genre_id) VALUES (?, ?)",
                fg.getFilm().getId(), fg.getGenre().getId()
        );
    }
}
