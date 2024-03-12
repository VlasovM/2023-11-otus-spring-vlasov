package ru.javlasov.springajax.services;

import ru.javlasov.springajax.dto.CommentDto;
import ru.javlasov.springajax.model.Comment;

import java.util.List;

public interface CommentService {

    CommentDto findById(long id);

    Comment create(String text, long bookId);

    Comment update(long id, String text, long bookId);

    void deleteById(long id);

    List<CommentDto> findCommentsByBookId(long bookId);

}
