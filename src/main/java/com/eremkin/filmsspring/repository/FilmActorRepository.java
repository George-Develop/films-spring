package com.eremkin.filmsspring.repository;

import com.eremkin.filmsspring.model.FilmActor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class FilmActorRepository {
    private final JdbcTemplate jdbc;

    public FilmActorRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public int save(FilmActor fa) {
        return jdbc.update(
                "INSERT INTO film_actors (film_id, actor_id, role_name) VALUES (?, ?, ?)",
                fa.getFilm().getId(), fa.getActor().getId(), fa.getRoleName()
        );
    }
}
