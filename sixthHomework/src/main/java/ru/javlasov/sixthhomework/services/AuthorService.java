package ru.javlasov.sixthhomework.services;

import ru.javlasov.sixthhomework.models.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    List<Author> findAll();

    Optional<Author> findById(long id);

}
