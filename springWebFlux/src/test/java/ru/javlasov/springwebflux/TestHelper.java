package ru.javlasov.springwebflux;

import ru.javlasov.springwebflux.dto.AuthorDto;
import ru.javlasov.springwebflux.dto.BookDto;
import ru.javlasov.springwebflux.dto.GenreDto;

import java.util.List;
import java.util.stream.IntStream;

public class TestHelper {

    public List<AuthorDto> getDbAuthorsDto() {
        return IntStream.range(1, 4).boxed()
                .map(id -> new AuthorDto(id.toString(), "Author_" + id))
                .toList();
    }

    public List<GenreDto> getDbGenresDto() {
        return IntStream.range(1, 4).boxed()
                .map(id -> new GenreDto(id.toString(), "Genre_" + id))
                .toList();
    }

    public List<BookDto> getDbBooks(List<AuthorDto> dbAuthors, List<GenreDto> dbGenres) {
        return IntStream.range(1, 4).boxed()
                .map(id -> new BookDto(id.toString(), "BookTitle_" + id, dbAuthors.get(id - 1), dbGenres.get(id - 1)))
                .toList();
    }

    public List<BookDto> getDbBooks() {
        return getDbBooks(getDbAuthorsDto(), getDbGenresDto());
    }
}
