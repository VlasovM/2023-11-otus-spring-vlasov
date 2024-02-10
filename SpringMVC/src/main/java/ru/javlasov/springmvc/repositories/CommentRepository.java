package ru.javlasov.springmvc.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.javlasov.springmvc.model.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends CrudRepository<Comment, Long> {

    Optional<Comment> findById(long id);

    List<Comment> findByBookId(long bookId);

    void deleteById(long id);

}
