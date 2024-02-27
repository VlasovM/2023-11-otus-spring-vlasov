package ru.javlasov.springmvc.services;

import ru.javlasov.springmvc.dto.BookCreateDto;
import ru.javlasov.springmvc.dto.BookUpdateDto;
import ru.javlasov.springmvc.dto.BookViewDto;

import java.util.List;

public interface BookService {

    BookUpdateDto findById(Long id);

    List<BookViewDto> findAll();

    BookCreateDto create(String title, long authorId, long genreId);

    BookUpdateDto update(Long id, String title, long authorId, long genreId);

    void deleteById(Long id);

}
