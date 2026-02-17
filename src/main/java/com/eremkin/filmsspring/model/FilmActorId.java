package com.eremkin.filmsspring.model;

import java.io.Serializable;
import java.util.Objects;

public class FilmActorId implements Serializable {
    private Long film;
    private Long actor;

    public FilmActorId() {}

    public FilmActorId(Long film, Long actor) {
        this.film = film;
        this.actor = actor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FilmActorId)) return false;
        FilmActorId that = (FilmActorId) o;
        return Objects.equals(film, that.film) && Objects.equals(actor, that.actor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(film, actor);
    }
}
