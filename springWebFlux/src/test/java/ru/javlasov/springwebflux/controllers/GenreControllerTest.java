package ru.javlasov.springwebflux.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import ru.javlasov.springwebflux.controller.GenreController;
import ru.javlasov.springwebflux.dto.GenreDto;
import ru.javlasov.springwebflux.services.GenreService;

import java.util.List;

import static org.mockito.Mockito.when;

@Import(GenreService.class)
@WebFluxTest(controllers = GenreController.class)
public class GenreControllerTest {

    @MockBean
    GenreService genreService;

    @MockBean
    GenreController genreController;

    @Autowired
    private WebTestClient webClient;


    @Test
    @DisplayName("Should return Genre list")
    void returnAuthorList() {
        var genres = getDbGenres();

        Flux<GenreDto> genreFlux = Flux.fromIterable(genres);
        when(genreService.findAll()).thenReturn(genreFlux);
        webClient.get()
                .uri("api/v1/genres")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(GenreDto.class);
    }

    private List<GenreDto> getDbGenres() {
        return List.of(new GenreDto("1", "Novel"));
    }


}