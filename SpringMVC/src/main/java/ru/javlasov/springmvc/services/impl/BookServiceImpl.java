package ru.javlasov.springmvc.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javlasov.springmvc.dto.BookCreateDto;
import ru.javlasov.springmvc.dto.BookUpdateDto;
import ru.javlasov.springmvc.dto.BookViewDto;
import ru.javlasov.springmvc.exceptions.EntityNotFoundException;
import ru.javlasov.springmvc.mappers.BookMapper;
import ru.javlasov.springmvc.model.Book;
import ru.javlasov.springmvc.repositories.AuthorRepository;
import ru.javlasov.springmvc.repositories.BookRepository;
import ru.javlasov.springmvc.repositories.GenreRepository;
import ru.javlasov.springmvc.services.BookService;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final AuthorRepository authorRepository;

    private final GenreRepository genreRepository;

    private final BookRepository bookRepository;

    private final BookMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public BookUpdateDto findById(Long id) {
        var book = bookRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Not found book with id = %d".formatted(id)));
        return mapper.entityToDtoUpdate(book);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookViewDto> findAll() {
        return mapper.entityToDtoView(bookRepository.findAll());
    }

    @Override
    @Transactional
    public BookCreateDto create(String title, long authorId, long genreId) {
        var author = authorRepository.findById(authorId)
                .orElseThrow(() -> new EntityNotFoundException("Author with id %d not found".formatted(authorId)));
        var genre = genreRepository.findById(genreId)
                .orElseThrow(() -> new EntityNotFoundException("Genre with id %d not found".formatted(genreId)));
        var book = new Book(title, author, genre);
        book = bookRepository.save(book);
        return mapper.entityToDtoCreate(book);
    }

    @Override
    @Transactional
    public BookUpdateDto update(Long id, String title, long authorId, long genreId) {
        var author = authorRepository.findById(authorId)
                .orElseThrow(() -> new EntityNotFoundException("Author with id %d not found".formatted(authorId)));
        var genre = genreRepository.findById(genreId)
                .orElseThrow(() -> new EntityNotFoundException("Genre with id %d not found".formatted(genreId)));
        var book = new Book(id, title, author, genre);
        book = bookRepository.save(book);
        return mapper.entityToDtoUpdate(book);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

}
