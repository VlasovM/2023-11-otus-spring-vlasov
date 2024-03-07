package ru.javlasov.springajax.repositories;

import io.micrometer.common.lang.NonNull;
import org.springframework.data.repository.CrudRepository;
import ru.javlasov.springajax.model.Genre;

import java.util.List;

public interface GenreRepository extends CrudRepository<Genre, Long> {

    @Override
    @NonNull
    List<Genre> findAll();

}
