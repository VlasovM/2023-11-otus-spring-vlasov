package ru.javlasov.springajax.controller;

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
import ru.javlasov.springajax.dto.BookCreateDto;
import ru.javlasov.springajax.dto.BookDto;
import ru.javlasov.springajax.dto.BookUpdateDto;
import ru.javlasov.springajax.services.BookService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
public class BookController {

    private final BookService bookService;

    @GetMapping("book/")
    public List<BookDto> findAll() {
        return bookService.findAll();
    }

    @PutMapping("book/{id}")
    public BookDto update(@PathVariable(name = "id") Long id,
                          @Valid @RequestBody BookUpdateDto bookUpdateDto) {
        bookUpdateDto.setId(id);
        return bookService.update(bookUpdateDto);
    }

    @DeleteMapping("book/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(name = "id") Long id) {
        bookService.deleteById(id);
    }

    @PostMapping("book/")
    @ResponseStatus(HttpStatus.CREATED)
    public BookDto create(@Valid @RequestBody BookCreateDto newBook) {
        return bookService.create(newBook);
    }

}
