package com.eremkin.filmsspring.repository;

import com.eremkin.filmsspring.model.Film;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FilmRepository {
    private final JdbcTemplate jdbc;

    public FilmRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public int save(Film film) {
        return jdbc.update(
                "INSERT INTO films (title, description, release_year) VALUES (?, ?, ?)",
                film.getTitle(), film.getDescription(), film.getReleaseYear()
        );
    }

    public List<Film> findAll() {
        return jdbc.query(
                "SELECT * FROM films",
                (rs, rowNum) -> new Film(
                        rs.getLong("id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getInt("release_year")
                )
        );
    }
}
