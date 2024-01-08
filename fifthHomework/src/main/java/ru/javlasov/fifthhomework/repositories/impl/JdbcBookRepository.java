package ru.javlasov.fifthhomework.repositories.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.javlasov.fifthhomework.exceptions.EntityNotFoundException;
import ru.javlasov.fifthhomework.models.Author;
import ru.javlasov.fifthhomework.models.Book;
import ru.javlasov.fifthhomework.models.Genre;
import ru.javlasov.fifthhomework.repositories.BookRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JdbcBookRepository implements BookRepository {

    private final NamedParameterJdbcOperations jdbcOperations;

    @Override
    public Optional<Book> findById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        try {
            var result = jdbcOperations.queryForObject("""
                SELECT t1.id as id, t1.title as title, t2.id as book_id, t2.full_name as book_name, 
                    t3.id as genre_id, t3.name as genre_name FROM BOOKS t1
                    JOIN AUTHORS t2 ON t1.author = t2.id
                    JOIN GENRES t3 ON t1.genre = t3.id 
                    WHERE t1.id = :id
                """, params, new BookRowMapper());
            return Optional.ofNullable(result);
        } catch (DataAccessException dataAccessException) {
            throw new EntityNotFoundException("Cannot find Book with id %d".formatted(id));
        }
    }

    @Override
    public List<Book> findAll() {
        return jdbcOperations.query("""
                SELECT t1.id as id, t1.title as title, t2.id as book_id, t2.full_name as book_name, 
                        t3.id as genre_id, t3.name as genre_name FROM BOOKS t1
                        JOIN AUTHORS t2 ON t1.author = t2.id
                        JOIN GENRES t3 ON t1.genre = t3.id
                """, new BookRowMapper());
    }

    @Override
    public Book save(Book book) {
        if (book.getId() == 0) {
            return insert(book);
        }
        return update(book);
    }

    @Override
    public void deleteById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        jdbcOperations.update("DELETE FROM BOOKS WHERE id = :id", params);
    }

    private Book insert(Book book) {
        var keyHolder = new GeneratedKeyHolder();
        var sqlParameterSource = new MapSqlParameterSource(Map.of("title", book.getTitle(),
                "author", book.getAuthor().getId(), "genre", book.getGenre().getId()));
        jdbcOperations.update("INSERT INTO BOOKS (title, author, genre) VALUES (:title, :author, :genre)",
                sqlParameterSource, keyHolder, new String[]{"id"});
        book.setId(keyHolder.getKeyAs(Long.class));
        return book;
    }

    private Book update(Book book) {
        var sqlParameterSource = new MapSqlParameterSource(Map.of("id", book.getId(), "title", book.getTitle(),
                "author", book.getAuthor().getId(), "genre", book.getGenre().getId()));
        var update = jdbcOperations.update("UPDATE BOOKS SET title = :title, author = :author, genre = :genre " +
                "WHERE id = :id", sqlParameterSource);
        if (update == 0) {
            throw new EntityNotFoundException("Cannot update book with id = %d".formatted(book.getId()));
        }
        return book;
    }

    @RequiredArgsConstructor
    private static class BookRowMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            var id = rs.getLong("id");
            var title = rs.getString("title");
            var author = new Author(rs.getLong("book_id"), rs.getString("book_name"));
            var genre = new Genre(rs.getLong("genre_id"), rs.getString("genre_name"));
            return new Book(id, title, author, genre);
        }
    }

}
