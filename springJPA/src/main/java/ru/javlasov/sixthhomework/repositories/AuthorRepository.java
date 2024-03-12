package ru.javlasov.sixthhomework.repositories;

import ru.javlasov.sixthhomework.models.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository {

    List<Author> findAll();

    Optional<Author> findById(long id);

}
