package ru.javlasov.seventhhomework.services;

import ru.javlasov.seventhhomework.models.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    List<Author> findAll();

    Optional<Author> findById(long id);

}
