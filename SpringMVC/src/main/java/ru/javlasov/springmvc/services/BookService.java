package ru.javlasov.springmvc.services;

import ru.javlasov.springmvc.dto.BookDto;
import ru.javlasov.springmvc.dto.BookUpdateDto;

import java.util.List;

public interface BookService {

    BookUpdateDto findById(Long id);

    List<BookDto> findAll();

    BookDto create(String title, long authorId, long genreId);

    BookDto update(Long id, String title, long authorId, long genreId);

    void deleteById(Long id);

}
