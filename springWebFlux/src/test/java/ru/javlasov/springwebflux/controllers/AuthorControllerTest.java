package ru.javlasov.springwebflux.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import ru.javlasov.springwebflux.TestConfig;
import ru.javlasov.springwebflux.controller.AuthorController;
import ru.javlasov.springwebflux.dto.AuthorDto;
import ru.javlasov.springwebflux.services.AuthorService;

import java.util.List;

import static org.mockito.Mockito.when;

@Import(AuthorService.class)
@WebFluxTest(controllers = AuthorController.class)
@ContextConfiguration(classes = TestConfig.class)
class AuthorControllerTest {

    @MockBean
    AuthorService authorService;

    @MockBean
    AuthorController authorController;

    @Autowired
    private WebTestClient webClient;


    @Test
    @DisplayName("Should return Author list")
    void returnAuthorList() {
        var authors = getDbAuthorsDto();
        Flux<AuthorDto> authorFlux = Flux.fromIterable(authors);
        when(authorService.findAll()).thenReturn(authorFlux);
        webClient.get()
                .uri("/api/v1/authors")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(AuthorDto.class);
    }

    private List<AuthorDto> getDbAuthorsDto() {
        return List.of(new AuthorDto("1", "Nikolay Gogol"));
    }

}