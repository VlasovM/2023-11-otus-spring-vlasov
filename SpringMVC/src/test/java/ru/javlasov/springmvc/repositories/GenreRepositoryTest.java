package ru.javlasov.springmvc.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.javlasov.springmvc.model.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DisplayName("Test genre repository")
class GenreRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private GenreRepository genreRepository;

    @Test
    @DisplayName("find all genres")
    void findAll() {
        var expected = getDbGenres();
        var actual = genreRepository.findAll();

        assertThat(expected.size()).isEqualTo(actual.size());
        assertThat(expected.get(0).getId()).isEqualTo(actual.get(0).getId());
    }

    @DisplayName("find genre by id")
    @ParameterizedTest
    @MethodSource("getDbGenres")
    void findById(Genre expectedGenre) {
        var actualGenre = genreRepository.findById(1);
        assertThat(actualGenre).isPresent();
        assertThat(actualGenre.get().getId()).isEqualTo(expectedGenre.getId());
        assertThat(actualGenre.get().getName()).isEqualTo(expectedGenre.getName());
    }

    private static List<Genre> getDbGenres() {
        return List.of(new Genre(1, "Novel"));
    }
}