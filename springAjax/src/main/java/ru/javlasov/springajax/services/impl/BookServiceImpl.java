package ru.javlasov.springajax.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javlasov.springajax.dto.BookCreateDto;
import ru.javlasov.springajax.dto.BookDto;
import ru.javlasov.springajax.dto.BookUpdateDto;
import ru.javlasov.springajax.exceptions.NotFoundException;
import ru.javlasov.springajax.mappers.BookMapper;
import ru.javlasov.springajax.repositories.AuthorRepository;
import ru.javlasov.springajax.repositories.BookRepository;
import ru.javlasov.springajax.repositories.GenreRepository;
import ru.javlasov.springajax.services.BookService;

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
    public BookDto findById(Long id) {
        var book = bookRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Not found book with id = %d".formatted(id)));
        return mapper.entityToDtoView(book);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookDto> findAll() {
        return mapper.entityToDtoView(bookRepository.findAll());
    }

    @Override
    @Transactional
    public BookDto create(BookCreateDto bookCreateDto) {
        var author = authorRepository.findById(bookCreateDto.getAuthorId())
                .orElseThrow(() -> new NotFoundException("Author with id %d not found"
                        .formatted(bookCreateDto.getAuthorId())));
        var genre = genreRepository.findById(bookCreateDto.getGenreId())
                .orElseThrow(() -> new NotFoundException("Genre with id %d not found"
                        .formatted(bookCreateDto.getGenreId())));
        var book = mapper.dtoCreateToEntity(bookCreateDto, author, genre);
        book = bookRepository.save(book);
        return mapper.entityToDtoView(book);
    }

    @Override
    @Transactional
    public BookDto update(BookUpdateDto bookUpdateDto) {
        var author = authorRepository.findById(bookUpdateDto.getAuthorId())
                .orElseThrow(() -> new NotFoundException("Author with id %d not found"
                        .formatted(bookUpdateDto.getAuthorId())));
        var genre = genreRepository.findById(bookUpdateDto.getGenreId())
                .orElseThrow(() -> new NotFoundException("Genre with id %d not found"
                        .formatted(bookUpdateDto.getGenreId())));
        bookRepository.findById(bookUpdateDto.getId()).orElseThrow(
                () -> new NotFoundException(String.format("Not fount book with id = %d", bookUpdateDto.getId())));
        var book = mapper.dtoUpdateToEntity(bookUpdateDto, author, genre);
        book = bookRepository.save(book);
        return mapper.entityToDtoView(book);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

}
