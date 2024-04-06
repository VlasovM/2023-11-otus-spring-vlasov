package ru.javlasov.springwebflux.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import ru.javlasov.springwebflux.models.Book;

public interface BookRepository extends ReactiveMongoRepository<Book, String> {
}
