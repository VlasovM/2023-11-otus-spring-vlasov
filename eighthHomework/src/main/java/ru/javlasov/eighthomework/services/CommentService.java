package ru.javlasov.eighthomework.services;

import ru.javlasov.eighthomework.models.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {

    Optional<Comment> findById(String id);

    Comment create(String text, String bookId);

    Comment update(String id, String text, String bookId);

    void deleteById(String id);

    List<Comment> findCommentsByBookId(String bookId);

}
