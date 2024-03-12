package ru.javlasov.sixthhomework.repositories.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.javlasov.sixthhomework.models.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DisplayName("Author repository test")
@Import({JpaGenreRepository.class})
class JpaGenreRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private JpaGenreRepository genreRepository;

    private List<Genre> dbGenres;

    @BeforeEach
    void setUp() {
        dbGenres = getDbGenres();
    }

    @Test
    @DisplayName("Get all genres")
    void findAll() {
        var actualGenres = genreRepository.findAll();
        var expectedGenre = dbGenres;
        assertThat(actualGenres).containsExactlyElementsOf(expectedGenre);
    }

    @DisplayName("Get genre by id")
    @ParameterizedTest
    @MethodSource("getDbGenres")
    void shouldGenreById(Genre expectedGenre) {
        var actualAuthor = genreRepository.findById(expectedGenre.getId());
        assertThat(actualAuthor).isPresent().get().isEqualTo(expectedGenre);
    }

    private static List<Genre> getDbGenres() {
        return List.of(new Genre(1, "Novel"));
    }

}