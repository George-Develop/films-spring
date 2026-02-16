package com.eremkin.filmsspring.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HomeControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private WebTestClient webClient;

    @Test
    void homePageShouldReturnOk() {
        webClient.get()
                .uri("/")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .consumeWith(response -> {
                    assert response.getResponseBody() != null;
                    assert response.getResponseBody().contains("Films-Spring");
                });
    }

    @Test
    void actorsPageShouldReturnOk() {
        webClient.get()
                .uri("/actors")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .consumeWith(response -> {
                    assert response.getResponseBody() != null;
                    assert response.getResponseBody().contains("Страница актёров");
                });
    }

    @Test
    void genresPageShouldReturnOk() {
        webClient.get()
                .uri("/genres")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .consumeWith(response -> {
                    assert response.getResponseBody() != null;
                    assert response.getResponseBody().contains("Страница жанров");
                });
    }

    @Test
    void filmsPageShouldReturnOk() {
        webClient.get()
                .uri("/films")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .consumeWith(response -> {
                    assert response.getResponseBody() != null;
                    assert response.getResponseBody().contains("Страница фильмов");
                });
    }
}
