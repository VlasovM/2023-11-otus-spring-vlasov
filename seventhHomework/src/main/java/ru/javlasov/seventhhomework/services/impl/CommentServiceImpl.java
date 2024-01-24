package ru.javlasov.seventhhomework.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javlasov.seventhhomework.exceptions.EntityNotFoundException;
import ru.javlasov.seventhhomework.models.Comment;
import ru.javlasov.seventhhomework.repositories.BookRepository;
import ru.javlasov.seventhhomework.repositories.CommentRepository;
import ru.javlasov.seventhhomework.services.CommentService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final BookRepository bookRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<Comment> findById(long id) {
        return commentRepository.findById(id);
    }

    @Override
    @Transactional
    public Comment create(String text, long bookId) {
        var book = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Not found book with id = %d".formatted(bookId)));
        var comment = new Comment(text, book);
        return commentRepository.save(comment);
    }

    @Override
    @Transactional
    public Comment update(long id, String text, long bookId) {
        var comment = commentRepository
                .findById(id).orElseThrow(() ->
                        new EntityNotFoundException("Comment with id = %d not found".formatted(id)));
        if (comment.getBook().getId() != bookId) {
            throw new EntityNotFoundException("Incorrect book id. Expected: %d, Actual: %d"
                    .formatted(comment.getBook().getId(), bookId));
        }
        comment.setText(text);
        return commentRepository.save(comment);
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        commentRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comment> findCommentsByBookId(long bookId) {
        return commentRepository.findByBookId(bookId);
    }

}
