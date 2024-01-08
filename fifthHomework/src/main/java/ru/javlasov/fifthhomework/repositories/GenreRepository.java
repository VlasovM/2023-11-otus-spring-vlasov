package ru.javlasov.fifthhomework.repositories;

import ru.javlasov.fifthhomework.models.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepository {

    List<Genre> findAll();

    Optional<Genre> findById(long id);

}
