package ru.javlasov.springajax.mappers;

import org.mapstruct.Mapper;
import ru.javlasov.springajax.dto.AuthorDto;
import ru.javlasov.springajax.model.Author;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    AuthorDto entityToDto(Author author);

    List<AuthorDto> entityToDto(List<Author> authors);

}
