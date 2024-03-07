package ru.javlasov.springajax.services;

import ru.javlasov.springajax.dto.BookCreateDto;
import ru.javlasov.springajax.dto.BookDto;
import ru.javlasov.springajax.dto.BookUpdateDto;

import java.util.List;

public interface BookService {

    BookUpdateDto findById(Long id);

    List<BookDto> findAll();

    BookDto create(BookCreateDto bookCreateDto);

    BookDto update(BookUpdateDto bookUpdateDto);

    void deleteById(Long id);

}
