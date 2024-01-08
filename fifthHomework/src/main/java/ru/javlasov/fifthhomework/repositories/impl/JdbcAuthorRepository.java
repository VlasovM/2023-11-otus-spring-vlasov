package ru.javlasov.fifthhomework.repositories.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.javlasov.fifthhomework.exceptions.EntityNotFoundException;
import ru.javlasov.fifthhomework.models.Author;
import ru.javlasov.fifthhomework.repositories.AuthorRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JdbcAuthorRepository implements AuthorRepository {

    private final NamedParameterJdbcOperations jdbcOperations;

    @Override
    public List<Author> findAll() {
        return jdbcOperations.query("SELECT id, full_name FROM AUTHORS", new AuthorRowMapper());
    }

    @Override
    public Optional<Author> findById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        try {
            var result = jdbcOperations.queryForObject("SELECT id, full_name FROM AUTHORS WHERE id = :id",
                    params, new AuthorRowMapper());
            return Optional.ofNullable(result);
        } catch (DataAccessException dataAccessException) {
            throw new EntityNotFoundException("Not found author with id = %d".formatted(id));
        }

    }

    private static class AuthorRowMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet rs, int i) throws SQLException {
            var id = rs.getLong("id");
            var fullName = rs.getString("full_name");
            return new Author(id, fullName);
        }
    }

}
