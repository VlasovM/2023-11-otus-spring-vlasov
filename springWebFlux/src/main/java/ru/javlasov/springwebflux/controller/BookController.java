package ru.javlasov.springwebflux.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.javlasov.springwebflux.dto.BookCreateDto;
import ru.javlasov.springwebflux.dto.BookDto;
import ru.javlasov.springwebflux.dto.BookUpdateDto;
import ru.javlasov.springwebflux.services.BookService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
public class BookController {

    private final BookService bookService;

    @GetMapping("book/")
    public Flux<BookDto> findAll() {
        return bookService.findAll();
    }

    @PutMapping("book/{id}")
    public Mono<BookDto> update(@PathVariable(name = "id") String id,
                                @Valid @RequestBody BookUpdateDto bookUpdateDto) {
        bookUpdateDto.setId(id);
        return bookService.update(bookUpdateDto);
    }

    @DeleteMapping("book/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(name = "id") String id) {
        bookService.deleteById(id);
    }

    @PostMapping("book/")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<BookDto> create(@Valid @RequestBody BookCreateDto newBook) {
        return bookService.create(newBook);
    }

}
