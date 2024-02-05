package ru.javlasov.eighthhomework.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.javlasov.eighthhomework.models.Book;

@RequiredArgsConstructor
@Component
public class BookConverter {

    private final AuthorConverter authorConverter;

    private final GenreConverter genreConverter;

    public String bookToString(Book book) {
        return "Id: %s, title: %s, author: {%s}, genre: {%s}".formatted(
                book.getId(),
                book.getTitle(),
                authorConverter.authorToString(book.getAuthor()),
                genreConverter.genreToString(book.getGenre()));
    }

}
