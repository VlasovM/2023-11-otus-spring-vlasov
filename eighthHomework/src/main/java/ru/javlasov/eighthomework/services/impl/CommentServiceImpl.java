package ru.javlasov.eighthomework.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javlasov.eighthomework.exceptions.EntityNotFoundException;
import ru.javlasov.eighthomework.models.Comment;
import ru.javlasov.eighthomework.repositories.BookRepository;
import ru.javlasov.eighthomework.repositories.CommentRepository;
import ru.javlasov.eighthomework.services.CommentService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final BookRepository bookRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<Comment> findById(String id) {
        return commentRepository.findById(id);
    }

    @Override
    @Transactional
    public Comment create(String text, String bookId) {
        var book = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Not found book with id = %s".formatted(bookId)));
        var comment = new Comment(text, book);
        return commentRepository.save(comment);
    }

    @Override
    @Transactional
    public Comment update(String id, String text, String bookId) {
        var comment = commentRepository
                .findById(id).orElseThrow(() ->
                        new EntityNotFoundException("Comment with id = %s not found".formatted(id)));
        if (!comment.getBook().getId().equals(bookId)) {
            throw new EntityNotFoundException("Incorrect book id. Expected: %s, Actual: %s"
                    .formatted(comment.getBook().getId(), bookId));
        }
        comment.setText(text);
        return commentRepository.save(comment);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        commentRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comment> findCommentsByBookId(String bookId) {
        return commentRepository.findByBookId(bookId);
    }

}