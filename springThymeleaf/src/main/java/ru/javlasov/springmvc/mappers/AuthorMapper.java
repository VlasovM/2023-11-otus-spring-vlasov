package ru.javlasov.springmvc.mappers;

import org.mapstruct.Mapper;
import ru.javlasov.springmvc.dto.AuthorDto;
import ru.javlasov.springmvc.model.Author;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    AuthorDto entityToDto(Author author);

    List<AuthorDto> entityToDto(List<Author> authors);

}
