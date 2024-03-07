package ru.javlasov.springajax.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javlasov.springajax.dto.BookDto;
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

}
