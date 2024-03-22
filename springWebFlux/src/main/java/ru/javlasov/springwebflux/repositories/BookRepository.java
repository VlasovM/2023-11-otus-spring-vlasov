package ru.javlasov.springwebflux.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.javlasov.springwebflux.models.Book;

public interface BookRepository extends ReactiveMongoRepository<Book, String> {

    Mono<Book> findById(String id);

    Flux<Book> findAll();

}
