package ru.javlasov.springajax.repositories;

import org.springframework.data.repository.ListCrudRepository;
import ru.javlasov.springajax.model.Author;

public interface AuthorRepository extends ListCrudRepository<Author, Long> {
}
