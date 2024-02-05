package ru.javlasov.eighthhomework.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javlasov.eighthhomework.exceptions.EntityNotFoundException;
import ru.javlasov.eighthhomework.models.Book;
import ru.javlasov.eighthhomework.repositories.AuthorRepository;
import ru.javlasov.eighthhomework.repositories.BookRepository;
import ru.javlasov.eighthhomework.repositories.GenreRepository;
import ru.javlasov.eighthhomework.services.BookService;

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
    public Optional<Book> findById(String id) {
        return bookRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    @Transactional
    public Book create(String title, String authorId, String genreId) {
        return save(null, title, authorId, genreId);
    }

    @Override
    @Transactional
    public Book update(String id, String title, String authorId, String genreId) {
        bookRepository
                .findById(id).orElseThrow(() ->
                        new EntityNotFoundException("Not found book with id = %s".formatted(id)));
        return save(id, title, authorId, genreId);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        bookRepository.deleteById(id);
    }

    private Book save(String id, String title, String authorId, String genreId) {
        var author = authorRepository.findById(authorId)
                .orElseThrow(() -> new EntityNotFoundException("Author with id %s not found".formatted(authorId)));
        var genre = genreRepository.findById(genreId)
                .orElseThrow(() -> new EntityNotFoundException("Genre with id %s not found".formatted(genreId)));
        var book = id == null ? new Book(title, author, genre) : new Book(id, title, author, genre);
        return bookRepository.save(book);
    }
}
