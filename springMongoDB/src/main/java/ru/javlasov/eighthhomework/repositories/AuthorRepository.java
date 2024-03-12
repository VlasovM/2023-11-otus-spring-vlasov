package ru.javlasov.eighthhomework.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.javlasov.eighthhomework.models.Author;

import java.util.List;

public interface AuthorRepository extends MongoRepository<Author, String> {

    @Override
    List<Author> findAll();

}
