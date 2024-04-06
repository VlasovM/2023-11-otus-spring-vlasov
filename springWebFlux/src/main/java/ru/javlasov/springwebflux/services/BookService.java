package ru.javlasov.springwebflux.services;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.javlasov.springwebflux.dto.BookCreateDto;
import ru.javlasov.springwebflux.dto.BookDto;
import ru.javlasov.springwebflux.dto.BookUpdateDto;

public interface BookService {

    Flux<BookDto> findAll();

    Mono<BookDto> create(BookCreateDto dto);

    Mono<BookDto> update(BookUpdateDto dto);

    Mono<Void> deleteById(String id);

}
