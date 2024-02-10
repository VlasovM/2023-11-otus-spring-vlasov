package ru.javlasov.eighthhomework.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import ru.javlasov.eighthhomework.models.Author;
import ru.javlasov.eighthhomework.models.Book;
import ru.javlasov.eighthhomework.models.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@DisplayName("Test book repository")
class BookRepositoryTest {

    @Autowired
    BookRepository bookRepository;

    @DisplayName("Should get book by id")
    @ParameterizedTest
    @MethodSource("getBooks")
    void shouldReturnBookById(Book expectedBook) {
        var actualBook = bookRepository.findById(expectedBook.getId());
        assertThat(actualBook).isPresent();
        assertThat(actualBook.get().getId()).isEqualTo(expectedBook.getId());
        assertThat(actualBook.get().getTitle()).isEqualTo(expectedBook.getTitle());
        assertThat(actualBook.get().getAuthor().getId()).isEqualTo(expectedBook.getAuthor().getId());
        assertThat(actualBook.get().getGenre().getId()).isEqualTo(expectedBook.getGenre().getId());
    }

    @Test
    @DisplayName("Find all books")
    void findAll() {
        var actualBooks = bookRepository.findAll();
        var expectedBooks = getBooks();

        assertThat(actualBooks.size()).isEqualTo(expectedBooks.size());
        assertThat(actualBooks.get(0).getId()).isEqualTo(expectedBooks.get(0).getId());
        assertThat(actualBooks.get(1).getId()).isEqualTo(expectedBooks.get(1).getId());
    }

    @Test
    @DisplayName("Delete book by id")
    void deleteById() {
        assertThat(bookRepository.findById("1")).isPresent();
        bookRepository.deleteById("1");
        assertThat(bookRepository.findById("1")).isEmpty();
    }

    private static List<Author> getDbAuthors() {
        return List.of(new Author("1", "Mikhail Lermontov"), new Author("2", "Nikolay Gogol"));
    }

    private static List<Genre> getDbGenres() {
        return List.of(new Genre("1", "Novel"));
    }

    private static List<Book> getDbBooks(List<Author> dbAuthors, List<Genre> dbGenres) {
        return List.of(new Book("1", "Dead souls", dbAuthors.get(1), dbGenres.get(0)),
                new Book("2", "Heroes of our time", dbAuthors.get(0), dbGenres.get(0)));
    }

    private static List<Book> getBooks() {
        var dbAuthors = getDbAuthors();
        var dbGenres = getDbGenres();
        return getDbBooks(dbAuthors, dbGenres);
    }
}