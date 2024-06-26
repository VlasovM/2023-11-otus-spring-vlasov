package ru.javlasov.springwebflux.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.javlasov.springwebflux.dto.BookCreateDto;
import ru.javlasov.springwebflux.dto.BookDto;
import ru.javlasov.springwebflux.dto.BookUpdateDto;
import ru.javlasov.springwebflux.models.Author;
import ru.javlasov.springwebflux.models.Book;
import ru.javlasov.springwebflux.models.Genre;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {

    @Mapping(target = "authorId", source = "source.author.id")
    @Mapping(target = "genreId", source = "source.genre.id")
    BookCreateDto entityToDtoCreate(Book source);

    @Mapping(target = "authorId", source = "source.author.id")
    @Mapping(target = "genreId", source = "source.genre.id")
    BookUpdateDto entityToDtoUpdate(Book source);

    @Mapping(target = "author", source = "source.author")
    @Mapping(target = "genre", source = "source.genre")
    BookDto entityToDtoView(Book source);

    default Book dtoCreateToEntity(BookCreateDto source, Author author, Genre genre) {
        return new Book(source.getTitle(), author, genre);
    }

    @Mapping(source = "source.id", target = "id")
    @Mapping(source = "author", target = "author")
    @Mapping(source = "genre", target = "genre")
    Book dtoUpdateToEntity(BookUpdateDto source, Author author, Genre genre);

    List<BookDto> entityToDtoView(List<Book> source);

}
