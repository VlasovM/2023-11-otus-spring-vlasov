package ru.javlasov.springajax.repositories;

import org.springframework.data.repository.ListCrudRepository;
import ru.javlasov.springajax.model.Genre;

public interface GenreRepository extends ListCrudRepository<Genre, Long> {
}
