package ru.javlasov.springacl.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javlasov.springacl.dto.BookCreateDto;
import ru.javlasov.springacl.dto.BookDto;
import ru.javlasov.springacl.exceptions.NotFoundException;
import ru.javlasov.springacl.mappers.BookMapper;
import ru.javlasov.springacl.repositories.AuthorRepository;
import ru.javlasov.springacl.repositories.BookRepository;
import ru.javlasov.springacl.repositories.GenreRepository;
import ru.javlasov.springacl.services.BookService;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final AuthorRepository authorRepository;

    private final GenreRepository genreRepository;

    private final BookRepository bookRepository;

    private final BookMapper bookMapper;

    @Override
    @Transactional(readOnly = true)
    public BookDto findById(Long id) {
        var book = bookRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Not found book with id = %d".formatted(id)));
        return bookMapper.modelToDto(book);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookDto> findAll() {
        return bookMapper.modelToDtoList(bookRepository.findAll());
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
        var book = bookMapper.toModel(bookCreateDto, author, genre);
        book = bookRepository.save(book);
        return bookMapper.modelToDto(book);
    }

    @Override
    @Transactional
    public BookDto update(BookDto bookDto) {
        bookRepository.findById(bookDto.getId()).orElseThrow(
                () -> new NotFoundException(String.format("Not fount book with id = %d", bookDto.getId())));
        var book = bookMapper.dtoToModel(bookDto);
        book = bookRepository.save(book);
        return bookMapper.modelToDto(book);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

}
