package ru.javlasov.seventhhomework.services;

import ru.javlasov.seventhhomework.models.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    Optional<Book> findById(long id);

    List<Book> findAll();

    Book create(String title, long authorId, long genreId);

    Book update(long id, String title, long authorId, long genreId);

    void deleteById(long id);

}
