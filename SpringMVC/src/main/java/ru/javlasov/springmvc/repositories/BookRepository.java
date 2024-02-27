package ru.javlasov.springmvc.repositories;

import lombok.NonNull;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import ru.javlasov.springmvc.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends CrudRepository<Book, Long> {

    @EntityGraph(value = "author-genre-entity-graph")
    Optional<Book> findById(long id);

    @Override
    @EntityGraph(value = "author-genre-entity-graph")
    @NonNull
    List<Book> findAll();

}
