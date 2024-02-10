package ru.javlasov.eighthhomework.services;

import ru.javlasov.eighthhomework.models.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    List<Author> findAll();

    Optional<Author> findById(String id);

}
