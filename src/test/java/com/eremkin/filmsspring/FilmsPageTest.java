package com.eremkin.filmsspring;

import com.eremkin.filmsspring.model.Film;
import com.eremkin.filmsspring.model.Actor;
import com.eremkin.filmsspring.model.Genre;
import com.eremkin.filmsspring.repository.FilmRepository;
import com.eremkin.filmsspring.repository.ActorRepository;
import com.eremkin.filmsspring.repository.GenreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FilmsPageTest {

    @LocalServerPort
    private int port;

    @Autowired
    private WebTestClient webClient;

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private GenreRepository genreRepository;

    private Film testFilm;
    private Actor testActor;
    private Genre testGenre;

    @BeforeEach
    void setup() {
        // Очистим репозитории
        filmRepository.deleteAll();
        actorRepository.deleteAll();
        genreRepository.deleteAll();

        // Создаем тестовые данные
        testActor = new Actor();
        testActor.setFirstName("Иван");
        testActor.setLastName("Иванов");
        actorRepository.save(testActor);

        testGenre = new Genre();
        testGenre.setName("Комедия");
        genreRepository.save(testGenre);

        testFilm = new Film();
        testFilm.setTitle("Тестовый фильм");
        testFilm.setDescription("Описание фильма");
        testFilm.setReleaseYear(2026);
        testFilm.setActors(Set.of(testActor));
        testFilm.setGenres(Set.of(testGenre));
        filmRepository.save(testFilm);
    }

    @Test
    void filmsPageShouldReturnOkAndContainFilmData() {
        webClient.get()
                .uri("/films")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .consumeWith(response -> {
                    String body = response.getResponseBody();
                    assert body != null;
                    assert body.contains("Список фильмов");
                    assert body.contains("Тестовый фильм");
                    assert body.contains("Иван Иванов");
                    assert body.contains("Комедия");
                    assert !body.contains("Добавить фильм");
                    assert !body.contains("Удалить");
                    assert !body.contains("Изменить");
                });
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void filmsPageShouldShowAdminControls() {
        webClient.get()
                .uri("/films")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .consumeWith(response -> {
                    String body = response.getResponseBody();
                    assert body != null;
                    assert body.contains("Список фильмов");
                    assert body.contains("Тестовый фильм");
                    assert body.contains("Иван Иванов");
                    assert body.contains("Комедия");
                    assert body.contains("Добавить фильм");
                    assert body.contains("Удалить");
                    assert body.contains("Изменить");
                });
    }
}
