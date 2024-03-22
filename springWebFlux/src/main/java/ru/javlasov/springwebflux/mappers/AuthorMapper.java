package ru.javlasov.springwebflux.mappers;

import org.mapstruct.Mapper;
import ru.javlasov.springwebflux.dto.AuthorDto;
import ru.javlasov.springwebflux.models.Author;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    AuthorDto entityToDto(Author author);

}
