package ru.javlasov.baseauth.repositories;

import org.springframework.data.repository.ListCrudRepository;
import ru.javlasov.baseauth.model.Genre;

public interface GenreRepository extends ListCrudRepository<Genre, Long> {
}
