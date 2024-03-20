package ru.javlasov.baseauth.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.javlasov.baseauth.model.Author;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DisplayName("Test author repository")
class AuthorRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    @DisplayName("find all authors")
    void shouldGetAllAuthors() {
        var expectedAuthors = getDbAuthors();
        var actualAuthors = authorRepository.findAll();

        assertThat(actualAuthors.size()).isEqualTo(expectedAuthors.size());
        assertThat(actualAuthors.get(0).getId()).isEqualTo(expectedAuthors.get(0).getId());
        assertThat(actualAuthors.get(0).getFullName()).isEqualTo(expectedAuthors.get(0).getFullName());
        assertThat(actualAuthors.get(1).getId()).isEqualTo(expectedAuthors.get(1).getId());
        assertThat(actualAuthors.get(1).getFullName()).isEqualTo(expectedAuthors.get(1).getFullName());
    }


    private static List<Author> getDbAuthors() {
        return List.of(new Author(1, "Nikolay Gogol"), new Author(2, "Fedor Dostoevsky"));
    }

}