package ru.javlasov.seventhhomework.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javlasov.seventhhomework.exceptions.EntityNotFoundException;
import ru.javlasov.seventhhomework.models.Book;
import ru.javlasov.seventhhomework.repositories.AuthorRepository;
import ru.javlasov.seventhhomework.repositories.BookRepository;
import ru.javlasov.seventhhomework.repositories.GenreRepository;
import ru.javlasov.seventhhomework.services.BookService;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final AuthorRepository authorRepository;

    private final GenreRepository genreRepository;

    private final BookRepository bookRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<Book> findById(long id) {
        return bookRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    @Transactional
    public Book create(String title, long authorId, long genreId) {
        return save(0, title, authorId, genreId);
    }

    @Override
    @Transactional
    public Book update(long id, String title, long authorId, long genreId) {
        bookRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Not found book with id = %d".formatted(id)));
        return save(id, title, authorId, genreId);
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        bookRepository.deleteById(id);
    }

    private Book save(long id, String title, long authorId, long genreId) {
        var author = authorRepository.findById(authorId)
                .orElseThrow(() -> new EntityNotFoundException("Author with id %d not found".formatted(authorId)));
        var genre = genreRepository.findById(genreId)
                .orElseThrow(() -> new EntityNotFoundException("Genre with id %d not found".formatted(genreId)));
        var book = id == 0 ? new Book(title, author, genre) : new Book(id, title, author, genre);
        return bookRepository.save(book);
    }
}
