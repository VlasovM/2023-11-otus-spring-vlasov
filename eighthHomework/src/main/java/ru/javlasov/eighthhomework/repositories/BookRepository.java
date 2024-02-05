package ru.javlasov.eighthhomework.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.javlasov.eighthhomework.models.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends MongoRepository<Book, String> {

    Optional<Book> findById(String id);

    @Override
    List<Book> findAll();

    void deleteById(String id);

}
