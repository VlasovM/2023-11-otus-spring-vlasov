package ru.javlasov.eighthhomework.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.javlasov.eighthhomework.models.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends MongoRepository<Comment, String> {

    Optional<Comment> findById(String id);

    List<Comment> findByBookId(String bookId);

    void deleteById(String id);

}
