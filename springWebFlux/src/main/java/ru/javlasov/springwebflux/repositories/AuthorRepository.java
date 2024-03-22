package ru.javlasov.springwebflux.repositories;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;
import ru.javlasov.springwebflux.models.Author;

public interface AuthorRepository extends ReactiveMongoRepository<Author, Long> {

    @NotNull
    Mono<Author> findById(@NotNull String id);

}
