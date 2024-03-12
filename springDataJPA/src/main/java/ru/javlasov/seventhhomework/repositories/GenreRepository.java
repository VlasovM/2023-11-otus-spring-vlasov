package ru.javlasov.seventhhomework.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.javlasov.seventhhomework.models.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepository extends CrudRepository<Genre, Long> {

    @Override
    List<Genre> findAll();

    Optional<Genre> findById(long id);

}
