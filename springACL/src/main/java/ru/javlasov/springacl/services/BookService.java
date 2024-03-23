package ru.javlasov.springacl.services;

import ru.javlasov.springacl.dto.BookCreateDto;
import ru.javlasov.springacl.dto.BookDto;
import ru.javlasov.springacl.dto.BookUpdateDto;

import java.util.List;

public interface BookService {

    BookDto findById(Long id);

    BookUpdateDto findByIdByUpdate(Long id);

    List<BookDto> findAll();

    BookDto create(BookCreateDto bookCreateDto);

    BookDto update(BookUpdateDto bookUpdateDto);

    void deleteById(Long id);

}
