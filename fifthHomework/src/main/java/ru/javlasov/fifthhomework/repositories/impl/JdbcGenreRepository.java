package ru.javlasov.fifthhomework.repositories.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.javlasov.fifthhomework.exceptions.EntityNotFoundException;
import ru.javlasov.fifthhomework.models.Genre;
import ru.javlasov.fifthhomework.repositories.GenreRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JdbcGenreRepository implements GenreRepository {

    private final NamedParameterJdbcOperations jdbcOperations;

    @Override
    public List<Genre> findAll() {
        return jdbcOperations.query("SELECT id, name FROM GENRES", new GenreRowMapper());
    }

    @Override
    public Optional<Genre> findById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        try {
            var result = jdbcOperations.queryForObject("SELECT id, name FROM GENRES " +
                    "WHERE id = :id", params, new GenreRowMapper());
            return Optional.ofNullable(result);
        } catch (DataAccessException dataAccessException) {
            throw new EntityNotFoundException("Not found genre with id = %d".formatted(id));
        }
    }

    private static class GenreRowMapper implements RowMapper<Genre> {

        @Override
        public Genre mapRow(ResultSet rs, int i) throws SQLException {
            var id = rs.getLong("id");
            var name = rs.getString("name");
            return new Genre(id, name);
        }
    }

}
