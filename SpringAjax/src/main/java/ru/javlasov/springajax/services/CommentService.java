package ru.javlasov.springajax.services;

import ru.javlasov.springajax.model.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {

    Optional<Comment> findById(long id);

    Comment create(String text, long bookId);

    Comment update(long id, String text, long bookId);

    void deleteById(long id);

    List<Comment> findCommentsByBookId(long bookId);

}
