package com.eremkin.filmsspring.repository;

import com.eremkin.filmsspring.model.FilmActor;
import com.eremkin.filmsspring.model.FilmActorId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmActorRepository extends JpaRepository<FilmActor, FilmActorId> {
    List<FilmActor> findByFilmId(Long filmId);
    List<FilmActor> findByActorId(Long actorId);
}
