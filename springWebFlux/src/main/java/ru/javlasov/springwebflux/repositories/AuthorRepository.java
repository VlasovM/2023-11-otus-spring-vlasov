package ru.javlasov.springwebflux.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import ru.javlasov.springwebflux.models.Author;

public interface AuthorRepository extends ReactiveMongoRepository<Author, String> {
}
