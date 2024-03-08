package ru.javlasov.springajax.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public ResponseEntity<List<BookDto>> findAll() {
        return ResponseEntity.ok(bookService.findAll());
    }

    @PutMapping("book/{id}")
    public ResponseEntity<BookDto> update(@PathVariable(name = "id") Long id,
                                          @Valid @RequestBody BookUpdateDto bookUpdateDto) {
        System.out.println("Update book");
        bookUpdateDto.setId(id);
        return ResponseEntity.ok(bookService.update(bookUpdateDto));
    }

    @DeleteMapping("book/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        bookService.deleteById(id);
    }

    @PostMapping("book/")
    public ResponseEntity<BookDto> create(@Valid @RequestBody BookCreateDto newBook) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(bookService.create(newBook));
    }

}
