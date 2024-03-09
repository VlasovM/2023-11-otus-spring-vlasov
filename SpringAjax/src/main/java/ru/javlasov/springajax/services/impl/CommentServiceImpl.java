package ru.javlasov.springajax.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javlasov.springajax.dto.CommentDto;
import ru.javlasov.springajax.exceptions.NotFoundException;
import ru.javlasov.springajax.mappers.CommentMapper;
import ru.javlasov.springajax.model.Comment;
import ru.javlasov.springajax.repositories.BookRepository;
import ru.javlasov.springajax.repositories.CommentRepository;
import ru.javlasov.springajax.services.CommentService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final BookRepository bookRepository;

    private final CommentMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public CommentDto findById(long id) {
        var comment = commentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Comment with id = %d not found", id)));
        return mapper.entityToDto(comment);
    }

    @Override
    @Transactional
    public Comment create(String text, long bookId) {
        var book = bookRepository.findById(bookId)
                .orElseThrow(() -> new NotFoundException("Not found book with id = %d".formatted(bookId)));
        var comment = new Comment(text, book);
        return commentRepository.save(comment);
    }

    @Override
    @Transactional
    public Comment update(long id, String text, long bookId) {
        var comment = commentRepository
                .findById(id).orElseThrow(() ->
                        new NotFoundException("Comment with id = %d not found".formatted(id)));
        if (comment.getBook().getId() != bookId) {
            throw new IllegalArgumentException("Incorrect book id. Expected: %d, Actual: %d"
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
    public List<CommentDto> findCommentsByBookId(long bookId) {
        return mapper.entityToDtoList(commentRepository.findByBookId(bookId));
    }

}
