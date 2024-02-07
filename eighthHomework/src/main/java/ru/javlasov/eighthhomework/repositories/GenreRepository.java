package ru.javlasov.eighthhomework.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.javlasov.eighthhomework.models.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepository extends MongoRepository<Genre, String> {

    @Override
    List<Genre> findAll();

    Optional<Genre> findById(String id);

}
