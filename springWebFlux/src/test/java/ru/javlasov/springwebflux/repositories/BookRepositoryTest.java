package ru.javlasov.springwebflux.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import ru.javlasov.springwebflux.models.Author;
import ru.javlasov.springwebflux.models.Book;
import ru.javlasov.springwebflux.models.Genre;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository repository;

    private final Author author = new Author("4", "Nikolay Gogol");

    private final Genre genre = new Genre("4", "Novel");

    @Test
    @DisplayName("Should set id on save")
    void shouldSetIdOnSave() {
        Mono<Book> bookMono = repository.save(new Book("Nose",author, genre));

        StepVerifier
                .create(bookMono)
                .assertNext(book -> assertNotNull(book.getId()))
                .expectComplete()
                .verify();
    }

    @Test
    @DisplayName("Should return correct books quantity")
    void shouldReturnCorrectBooksQuantity() {
        StepVerifier
                .create(repository.findAll())
                .expectNextCount(3)
                .verifyComplete();
    }

}
