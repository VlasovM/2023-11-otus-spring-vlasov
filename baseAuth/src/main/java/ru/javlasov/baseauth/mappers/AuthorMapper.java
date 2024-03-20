package ru.javlasov.baseauth.mappers;

import org.mapstruct.Mapper;
import ru.javlasov.baseauth.dto.AuthorDto;
import ru.javlasov.baseauth.model.Author;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    AuthorDto entityToDto(Author author);

    List<AuthorDto> entityToDto(List<Author> authors);

}
