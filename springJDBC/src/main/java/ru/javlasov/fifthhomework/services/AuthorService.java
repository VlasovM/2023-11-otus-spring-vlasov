package ru.javlasov.fifthhomework.services;

import ru.javlasov.fifthhomework.models.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    List<Author> findAll();

    Optional<Author> findById(long id);

}
