package ru.javlasov.springwebflux.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import ru.javlasov.springwebflux.models.Genre;

public interface GenreRepository extends ReactiveMongoRepository<Genre, String> {
}
