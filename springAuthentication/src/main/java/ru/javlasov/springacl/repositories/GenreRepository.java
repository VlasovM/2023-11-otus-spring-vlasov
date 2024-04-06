package ru.javlasov.springacl.repositories;

import org.springframework.data.repository.ListCrudRepository;
import ru.javlasov.springacl.model.Genre;

public interface GenreRepository extends ListCrudRepository<Genre, Long> {
}
