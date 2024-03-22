package ru.javlasov.springwebflux.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.javlasov.springwebflux.dto.BookCreateDto;
import ru.javlasov.springwebflux.dto.BookDto;
import ru.javlasov.springwebflux.dto.BookUpdateDto;
import ru.javlasov.springwebflux.exceptions.NotFoundException;
import ru.javlasov.springwebflux.mappers.BookMapper;
import ru.javlasov.springwebflux.models.Book;
import ru.javlasov.springwebflux.repositories.AuthorRepository;
import ru.javlasov.springwebflux.repositories.BookRepository;
import ru.javlasov.springwebflux.repositories.GenreRepository;
import ru.javlasov.springwebflux.services.BookService;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final AuthorRepository authorRepository;

    private final GenreRepository genreRepository;

    private final BookRepository bookRepository;

    private final BookMapper mapper;

    @Override
    public Mono<BookDto> findById(String id) {
        return bookRepository.findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException("")))
                .map(mapper::entityToDtoView);
    }

    @Override
    public Flux<BookDto> findAll() {
        return bookRepository.findAll().map(mapper::entityToDtoView);
    }

    @Override
    public Mono<BookDto> create(BookCreateDto dto) {
        return Mono.zip(
                        authorRepository.findById(dto.getAuthorId())
                                .switchIfEmpty(Mono.error(new NotFoundException(""))),
                        genreRepository.findById(dto.getGenreId())
                                .switchIfEmpty(Mono.error(new NotFoundException(""))),
                        (author, genre) -> new Book(dto.getTitle(), author, genre))
                .flatMap(bookRepository::save)
                .map(mapper::entityToDtoView);
    }

    @Override
    public Mono<BookDto> update(BookUpdateDto bookUpdateDto) {
        return Mono.zip(
                        authorRepository.findById(bookUpdateDto.getAuthorId())
                                .switchIfEmpty(Mono.error(new NotFoundException(""))),
                        genreRepository.findById(bookUpdateDto.getGenreId())
                                .switchIfEmpty(Mono.error(new NotFoundException(""))))
                .flatMap(tuple -> bookRepository.findById(bookUpdateDto.getId())
                        .switchIfEmpty(Mono.error(new NotFoundException("")))
                        .flatMap(book -> {
                            book.setTitle(bookUpdateDto.getTitle());
                            book.setAuthor(tuple.getT1());
                            book.setGenre(tuple.getT2());
                            return bookRepository.save(book).map(mapper::entityToDtoView);
                        })
                );
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return bookRepository.deleteById(id);
    }

}
