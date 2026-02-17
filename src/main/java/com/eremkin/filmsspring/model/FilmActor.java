package com.eremkin.filmsspring.model;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "film_actors")
@IdClass(FilmActorId.class)
public class FilmActor {

    @Id
    @ManyToOne
    @JoinColumn(name = "film_id")
    private Film film;

    @Id
    @ManyToOne
    @JoinColumn(name = "actor_id")
    private Actor actor;

    @Column(name = "role_name")
    private String roleName;

    public FilmActor() {}
    public FilmActor(Film film, Actor actor, String roleName) {
        this.film = film;
        this.actor = actor;
        this.roleName = roleName;
    }
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Film getFilm() {
        return film;
    }
    public Actor getActor() {
        return actor;
    }
}
