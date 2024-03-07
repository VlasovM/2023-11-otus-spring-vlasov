package ru.javlasov.springajax.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.javlasov.springajax.model.Comment;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Long> {

    List<Comment> findByBookId(long bookId);

}
