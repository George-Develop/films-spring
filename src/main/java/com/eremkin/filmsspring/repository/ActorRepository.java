package com.eremkin.filmsspring.repository;

import com.eremkin.filmsspring.model.Actor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class ActorRepository {
    private final JdbcTemplate jdbc;

    public ActorRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public int save(Actor actor) {
        return jdbc.update(
                "INSERT INTO actors (first_name, last_name, birth_date) VALUES (?, ?, ?)",
                actor.getFirstName(), actor.getLastName(), actor.getBirthDate()
        );
    }

    public List<Actor> findAll() {
        return jdbc.query(
                "SELECT * FROM actors",
                (rs, rowNum) -> new Actor(
                        rs.getLong("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getObject("birth_date", LocalDate.class)
                )
        );
    }
}
