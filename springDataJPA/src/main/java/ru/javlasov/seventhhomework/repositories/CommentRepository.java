package ru.javlasov.seventhhomework.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.javlasov.seventhhomework.models.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends CrudRepository<Comment, Long> {

    Optional<Comment> findById(long id);

    List<Comment> findByBookId(long bookId);

    void deleteById(long id);

}
