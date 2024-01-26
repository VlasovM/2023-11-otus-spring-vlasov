package ru.javlasov.seventhhomework.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.javlasov.seventhhomework.models.Author;
import ru.javlasov.seventhhomework.models.Book;
import ru.javlasov.seventhhomework.models.Comment;
import ru.javlasov.seventhhomework.models.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CommentRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CommentRepository commentRepository;

    @DisplayName("Should get comment by id")
    @ParameterizedTest
    @MethodSource("getDbComments")
    void shouldGetCommentById(Comment expected) {
        var actualComment = commentRepository.findById(expected.getId());
        assertThat(actualComment).isPresent().get().isEqualTo(expected);
    }

    @DisplayName("Should get list comment by bookId")
    @Test
    void findByBookId() {
        var expectedComments = getDbComments();
        var actualComments = commentRepository.findByBookId(1);
        assertThat(actualComments).containsExactlyElementsOf(expectedComments);
    }

    @Test
    @DisplayName("Should delete comment by id")
    void deleteById() {
        var comment = commentRepository.findById(1L);
        assertThat(comment).isPresent();
        commentRepository.delete(comment.get());
        assertThat(commentRepository.findById(1L)).isEmpty();
    }

    private static List<Comment> getDbComments(List<Book> dbBooks) {
        return List.of(new Comment(1, "This book is cool", dbBooks.get(0)),
                new Comment(2, "Wow! This awesome!", dbBooks.get(0)));
    }

    private static List<Comment> getDbComments() {
        var dbBooks = getDbBooks();
        return getDbComments(dbBooks);
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