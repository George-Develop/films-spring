package com.eremkin.filmsspring.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "film_genres")
@IdClass(FilmGenreId.class)
public class FilmGenre implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "film_id")
    private Film film;

    @Id
    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;

    public FilmGenre() {}

    public FilmGenre(Film film, Genre genre) {
        this.film = film;
        this.genre = genre;
    }

    public Film getFilm() { return film; }
    public void setFilm(Film film) { this.film = film; }

    public Genre getGenre() { return genre; }
    public void setGenre(Genre genre) { this.genre = genre; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FilmGenre)) return false;
        FilmGenre that = (FilmGenre) o;
        return Objects.equals(film, that.film) && Objects.equals(genre, that.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(film, genre);
    }
}
