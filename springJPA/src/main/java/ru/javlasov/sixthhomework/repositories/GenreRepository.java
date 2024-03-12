package ru.javlasov.sixthhomework.repositories;

import ru.javlasov.sixthhomework.models.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepository {

    List<Genre> findAll();

    Optional<Genre> findById(long id);

}
