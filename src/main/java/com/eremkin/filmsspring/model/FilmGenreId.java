package com.eremkin.filmsspring.model;

import java.io.Serializable;
import java.util.Objects;

public class FilmGenreId implements Serializable {
    private Long film;
    private Long genre;

    public FilmGenreId() {}

    public FilmGenreId(Long film, Long genre) {
        this.film = film;
        this.genre = genre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FilmGenreId)) return false;
        FilmGenreId that = (FilmGenreId) o;
        return Objects.equals(film, that.film) && Objects.equals(genre, that.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(film, genre);
    }
}
