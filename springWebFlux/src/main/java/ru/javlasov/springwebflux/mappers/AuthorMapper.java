package ru.javlasov.springwebflux.mappers;

import org.mapstruct.Mapper;
import ru.javlasov.springwebflux.dto.AuthorDto;
import ru.javlasov.springwebflux.models.Author;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    AuthorDto entityToDto(Author author);

    List<AuthorDto> entityToDto(List<Author> authors);

}
