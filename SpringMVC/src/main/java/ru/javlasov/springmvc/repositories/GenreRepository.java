package ru.javlasov.springmvc.repositories;

import lombok.NonNull;
import org.springframework.data.repository.CrudRepository;
import ru.javlasov.springmvc.model.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepository extends CrudRepository<Genre, Long> {

    @Override
    @NonNull
    List<Genre> findAll();

    Optional<Genre> findById(long id);

}
