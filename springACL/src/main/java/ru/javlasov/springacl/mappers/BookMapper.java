package ru.javlasov.springacl.mappers;

import org.mapstruct.Mapper;
import ru.javlasov.springacl.dto.BookCreateDto;
import ru.javlasov.springacl.dto.BookDto;
import ru.javlasov.springacl.model.Author;
import ru.javlasov.springacl.model.Book;
import ru.javlasov.springacl.model.Genre;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {

    BookDto modelToDto(Book source);

    Book dtoToModel(BookDto bookDto);

    List<BookDto> modelToDtoList(List<Book> source);

    default Book toModel(BookCreateDto source, Author author, Genre genre) {
        return new Book(source.getTitle(), author, genre);
    }

}
