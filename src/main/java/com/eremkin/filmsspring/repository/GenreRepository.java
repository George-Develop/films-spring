package com.eremkin.filmsspring.repository;

import com.eremkin.filmsspring.model.Genre;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GenreRepository {
    private final JdbcTemplate jdbc;

    public GenreRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public int save(Genre genre) {
        return jdbc.update(
                "INSERT INTO genres (name) VALUES (?)",
                genre.getName()
        );
    }

    public List<Genre> findAll() {
        return jdbc.query(
                "SELECT * FROM genres",
                (rs, rowNum) -> new Genre(
                        rs.getLong("id"),
                        rs.getString("name")
                )
        );
    }
}
