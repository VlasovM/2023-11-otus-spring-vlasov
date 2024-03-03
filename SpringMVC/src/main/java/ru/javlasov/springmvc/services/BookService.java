package ru.javlasov.springmvc.services;

import ru.javlasov.springmvc.dto.BookCreateDto;
import ru.javlasov.springmvc.dto.BookDto;
import ru.javlasov.springmvc.dto.BookUpdateDto;

import java.util.List;

public interface BookService {

    BookUpdateDto findById(Long id);

    List<BookDto> findAll();

    BookDto create(BookCreateDto bookCreateDto);

    BookDto update(BookUpdateDto bookUpdateDto);

    void deleteById(Long id);

}
