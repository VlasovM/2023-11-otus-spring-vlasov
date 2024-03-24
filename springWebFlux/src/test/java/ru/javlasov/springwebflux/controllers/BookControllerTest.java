package ru.javlasov.springwebflux.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.javlasov.springwebflux.controller.BookController;
import ru.javlasov.springwebflux.dto.AuthorDto;
import ru.javlasov.springwebflux.dto.BookCreateDto;
import ru.javlasov.springwebflux.dto.BookDto;
import ru.javlasov.springwebflux.dto.BookUpdateDto;
import ru.javlasov.springwebflux.dto.GenreDto;
import ru.javlasov.springwebflux.services.BookService;

import java.util.List;

import static org.mockito.Mockito.when;


@Import(BookService.class)
@WebFluxTest(controllers = BookController.class)
public class BookControllerTest {

    @MockBean
    BookService bookService;

    @MockBean
    BookController bookController;

    @Autowired
    private WebTestClient webClient;


    @Test
    @DisplayName("Should get book list")
    void shouldGetBookList() {
        var books = getDbBooks();
        Flux<BookDto> bookFlux = Flux.fromIterable(books);
        when(bookService.findAll()).thenReturn(bookFlux);
        webClient.get()
                .uri("/api/v1/book/")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(BookDto.class);
    }

    @Test
    @DisplayName("Should create book")
    void shouldCreateBook() {
        var book = getBookCreateDto();
        webClient.post().uri("/api/v1/book/")
                .body(Mono.just(book), BookCreateDto.class)
                .exchange()
                .expectStatus().isCreated();
    }

    @Test
    @DisplayName("Should update book")
    void shouldUpdateBook() {
        var book = getBookUpdateDto();
        webClient.put().uri("/api/v1/book/{id}", "1")
                .body(Mono.just(book), BookUpdateDto.class)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    @DisplayName("Should delete book")
    void shouldDeleteBook() {
        Mono<Void> voidReturn = Mono.empty();
        Mockito.when(bookService.deleteById("1")).thenReturn(voidReturn);
        webClient.delete().uri("/api/v1/book/{id}", 1)
                .exchange()
                .expectStatus().isNoContent();
    }

    private List<BookDto> getDbBooks() {
        GenreDto genreDto = new GenreDto("1", "Novel");
        AuthorDto authorDto = new AuthorDto("1", "Nikolay Gogol");
        BookDto bookDto = new BookDto("1", "Nose", authorDto, genreDto);
        return List.of(bookDto);
    }

    private BookCreateDto getBookCreateDto() {
        return new BookCreateDto("Overcoat", "1", "1");
    }

    private BookUpdateDto getBookUpdateDto() {
        return new BookUpdateDto("1", "Overcoat", "1","1");
    }

}