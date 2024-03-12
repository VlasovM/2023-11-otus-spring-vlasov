package ru.javlasov.seventhhomework.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.javlasov.seventhhomework.models.Author;

import java.util.List;

public interface AuthorRepository extends CrudRepository<Author,Long> {

    @Override
    List<Author> findAll();

}
