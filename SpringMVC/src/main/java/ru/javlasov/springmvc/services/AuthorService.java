package ru.javlasov.springmvc.services;

import ru.javlasov.springmvc.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    List<Author> findAll();

    Optional<Author> findById(long id);

}
