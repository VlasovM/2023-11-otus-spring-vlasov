package ru.javlasov.sixthhomework.services;

import ru.javlasov.sixthhomework.models.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {

    Optional<Comment> findById(long id);

    Comment insert(String text, long bookId);

    Comment update(long id, String text, long bookId);

    void deleteById(long id);

    List<Comment> findCommentsByBookId(long bookId);

}
