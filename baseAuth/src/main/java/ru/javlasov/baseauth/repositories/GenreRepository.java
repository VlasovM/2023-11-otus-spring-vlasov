package ru.javlasov.baseauth.repositories;

import io.micrometer.common.lang.NonNull;
import org.springframework.data.repository.CrudRepository;
import ru.javlasov.baseauth.model.Genre;

import java.util.List;

public interface GenreRepository extends CrudRepository<Genre, Long> {

    @Override
    @NonNull
    List<Genre> findAll();

}
