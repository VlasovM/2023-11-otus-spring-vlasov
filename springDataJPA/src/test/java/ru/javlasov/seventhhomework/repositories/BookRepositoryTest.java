package ru.javlasov.seventhhomework.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.javlasov.seventhhomework.models.Author;
import ru.javlasov.seventhhomework.models.Book;
import ru.javlasov.seventhhomework.models.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class BookRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BookRepository bookRepository;

    private List<Author> dbAuthors;

    private List<Genre> dbGenres;

    private List<Book> dbBooks;

    @BeforeEach
    void setUp() {
        dbAuthors = getDbAuthors();
        dbGenres = getDbGenres();
        dbBooks = getDbBooks(dbAuthors, dbGenres);
    }

    @DisplayName("should get book by id")
    @ParameterizedTest
    @MethodSource("getDbBooks")
    void shouldReturnCorrectBookById(Book expectedBook) {
        var actualBook = bookRepository.findById(expectedBook.getId());
        assertThat(actualBook).isPresent();
        assertThat(actualBook.get().getId()).isEqualTo(expectedBook.getId());
        assertThat(actualBook.get().getAuthor().getId()).isEqualTo(expectedBook.getAuthor().getId());
        assertThat(actualBook.get().getGenre().getId()).isEqualTo(expectedBook.getGenre().getId());
        assertThat(actualBook.get().getTitle()).isEqualTo(expectedBook.getTitle());
    }

    @DisplayName("should get all books")
    @Test
    void shouldReturnCorrectBooksList() {
        var actualBooks = bookRepository.findAll();
        var expectedBooks = dbBooks;

        assertThat(actualBooks.size()).isEqualTo(expectedBooks.size());
        assertThat(actualBooks.get(0).getId()).isEqualTo(expectedBooks.get(0).getId());
        assertThat(actualBooks.get(1).getId()).isEqualTo(expectedBooks.get(1).getId());
    }

    @DisplayName("Should delete by id")
    @Test
    void shouldDeleteBook() {
        assertThat(bookRepository.findById(1L)).isPresent();
        bookRepository.deleteById(1L);
        assertThat(bookRepository.findById(1L)).isEmpty();
    }

    private static List<Author> getDbAuthors() {
        return List.of(new Author(1, "Nikolay Gogol"), new Author(2, "Fedor Dostoevsky"));
    }

    private static List<Genre> getDbGenres() {
        return List.of(new Genre(1, "Novel"));
    }

    private static List<Book> getDbBooks(List<Author> dbAuthors, List<Genre> dbGenres) {
        return List.of(new Book(1, "Dead souls", dbAuthors.get(0), dbGenres.get(0)),
                new Book(2, "Crime and punishment", dbAuthors.get(1), dbGenres.get(0)));
    }

    private static List<Book> getDbBooks() {
        var dbAuthors = getDbAuthors();
        var dbGenres = getDbGenres();
        return getDbBooks(dbAuthors, dbGenres);
    }

}