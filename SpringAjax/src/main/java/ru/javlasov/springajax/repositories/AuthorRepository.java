package ru.javlasov.springajax.repositories;

import io.micrometer.common.lang.NonNull;
import org.springframework.data.repository.CrudRepository;
import ru.javlasov.springajax.model.Author;

import java.util.List;

public interface AuthorRepository extends CrudRepository<Author, Long> {

    @Override
    @NonNull
    List<Author> findAll();

}
