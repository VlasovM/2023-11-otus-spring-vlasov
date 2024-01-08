package ru.javlasov.fifthhomework.repositories.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.javlasov.fifthhomework.models.Author;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@DisplayName("Author repository test")
@Import({JdbcAuthorRepository.class})
class JdbcAuthorRepositoryTest {

    @Autowired
    private JdbcAuthorRepository authorRepository;

    private List<Author> dbAuthors;

    @BeforeEach
    void setUp() {
        dbAuthors = getDbAuthors();
    }

    @DisplayName("Get all books")
    @Test
    void shouldGetAllAuthors() {
        var actualAuthors = authorRepository.findAll();
        var expectedAuthors = dbAuthors;
        assertThat(actualAuthors).containsExactlyElementsOf(expectedAuthors);
    }

    @DisplayName("Book by id")
    @ParameterizedTest
    @MethodSource("getDbAuthors")
    void shouldGetAuthorById(Author expectedAuthor) {
        var actualAuthor = authorRepository.findById(expectedAuthor.getId());
        assertThat(actualAuthor).isPresent().get().isEqualTo(expectedAuthor);
    }

    private static List<Author> getDbAuthors() {
        return List.of(new Author(1, "Nikolay Gogol"), new Author(2, "Fedor Dostoevsky"));
    }

}