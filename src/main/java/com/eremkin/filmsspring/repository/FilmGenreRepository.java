package com.eremkin.filmsspring.repository;

import com.eremkin.filmsspring.model.FilmGenre;
import com.eremkin.filmsspring.model.FilmGenreId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmGenreRepository extends JpaRepository<FilmGenre, FilmGenreId> {
    List<FilmGenre> findByFilmId(Long filmId);
    List<FilmGenre> findByGenreId(Long genreId);
}
