package ru.javlasov.springacl.repositories;

import org.springframework.data.repository.ListCrudRepository;
import ru.javlasov.springacl.model.Author;

public interface AuthorRepository extends ListCrudRepository<Author, Long> {
}
