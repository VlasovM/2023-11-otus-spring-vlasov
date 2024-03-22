package ru.javlasov.springwebflux.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.javlasov.springwebflux.dto.BookDto;
import ru.javlasov.springwebflux.models.Book;

@Mapper(componentModel = "spring")
public interface BookMapper {

    @Mapping(target = "author", source = "source.author.fullName")
    @Mapping(target = "genre", source = "source.genre.name")
    BookDto entityToDtoView(Book source);


}
