package ru.javlasov.eighthomework.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.javlasov.eighthomework.models.Author;

import java.util.List;

public interface AuthorRepository extends MongoRepository<Author, String> {

    @Override
    List<Author> findAll();

}
