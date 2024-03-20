package ru.javlasov.baseauth.repositories;

import org.springframework.data.repository.ListCrudRepository;
import ru.javlasov.baseauth.model.Author;

public interface AuthorRepository extends ListCrudRepository<Author, Long> {
}
