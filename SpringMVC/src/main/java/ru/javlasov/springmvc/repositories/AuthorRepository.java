package ru.javlasov.springmvc.repositories;

import lombok.NonNull;
import org.springframework.data.repository.CrudRepository;
import ru.javlasov.springmvc.model.Author;

import java.util.List;

public interface AuthorRepository extends CrudRepository<Author,Long> {

    @Override
    @NonNull
    List<Author> findAll();

}
