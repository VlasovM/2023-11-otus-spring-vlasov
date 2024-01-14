package ru.javlasov.sixthhomework.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.javlasov.sixthhomework.exceptions.EntityNotFoundException;
import ru.javlasov.sixthhomework.models.Comment;
import ru.javlasov.sixthhomework.repositories.impl.JpaBookRepository;
import ru.javlasov.sixthhomework.repositories.impl.JpaCommentRepository;
import ru.javlasov.sixthhomework.services.CommentService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final JpaCommentRepository commentRepository;

    private final JpaBookRepository bookRepository;

    @Override
    public Optional<Comment> findById(long id) {
        return commentRepository.findById(id);
    }

    @Override
    public Comment insert(String text, long bookId) {
        return commentRepository.saveOrUpdate(prepareCommentToSaveOrUpdate(0, text, bookId));
    }

    @Override
    public Comment update(long id, String text, long bookId) {
        return commentRepository.saveOrUpdate(prepareCommentToSaveOrUpdate(id, text, bookId));
    }

    @Override
    public void deleteById(long id) {
        commentRepository.delete(id);
    }

    @Override
    public List<Comment> findCommentsByBookId(long bookId) {
        return commentRepository.findAllCommentByBookId(bookId);
    }

    private Comment prepareCommentToSaveOrUpdate(long id, String text, long bookId) {
        var book = bookRepository.findById(bookId).orElseThrow(() ->
                new EntityNotFoundException("Not found book with id = %d".formatted(bookId)));
        return new Comment(id, text, book);
    }

}
