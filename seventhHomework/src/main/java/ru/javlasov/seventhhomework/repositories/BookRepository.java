package ru.javlasov.seventhhomework.repositories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import ru.javlasov.seventhhomework.models.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends CrudRepository<Book, Long> {

    Optional<Book> findById(long id);

    @Override
    @EntityGraph(attributePaths = {"genre", "author"})
    List<Book> findAll();

    void deleteById(long id);

}
