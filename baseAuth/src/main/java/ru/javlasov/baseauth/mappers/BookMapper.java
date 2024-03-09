package ru.javlasov.baseauth.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.javlasov.baseauth.dto.BookCreateDto;
import ru.javlasov.baseauth.dto.BookDto;
import ru.javlasov.baseauth.dto.BookUpdateDto;
import ru.javlasov.baseauth.model.Book;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {

    @Mapping(target = "authorId", source = "source.author.id")
    @Mapping(target = "genreId", source = "source.genre.id")
    BookCreateDto entityToDtoCreate(Book source);

    @Mapping(target = "authorId", source = "source.author.id")
    @Mapping(target = "genreId", source = "source.genre.id")
    BookUpdateDto entityToDtoUpdate(Book source);

    @Mapping(target = "author", source = "source.author.fullName")
    @Mapping(target = "genre", source = "source.genre.name")
    BookDto entityToDtoView(Book source);

    List<BookDto> entityToDtoView(List<Book> source);

}
