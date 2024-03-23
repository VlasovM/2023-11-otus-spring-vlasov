package ru.javlasov.springacl.mappers;

import org.mapstruct.Mapper;
import ru.javlasov.springacl.dto.AuthorDto;
import ru.javlasov.springacl.model.Author;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    AuthorDto entityToDto(Author author);

    List<AuthorDto> entityToDto(List<Author> authors);

}
