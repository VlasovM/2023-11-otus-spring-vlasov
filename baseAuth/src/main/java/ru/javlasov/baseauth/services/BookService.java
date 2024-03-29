package ru.javlasov.baseauth.services;

import ru.javlasov.baseauth.dto.BookCreateDto;
import ru.javlasov.baseauth.dto.BookDto;
import ru.javlasov.baseauth.dto.BookUpdateDto;

import java.util.List;

public interface BookService {

    BookDto findById(Long id);

    BookUpdateDto findByIdByUpdate(Long id);

    List<BookDto> findAll();

    BookDto create(BookCreateDto bookCreateDto);

    BookDto update(BookUpdateDto bookUpdateDto);

    void deleteById(Long id);

}
