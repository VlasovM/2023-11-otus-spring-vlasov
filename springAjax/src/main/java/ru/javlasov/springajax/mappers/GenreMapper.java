package ru.javlasov.springajax.mappers;

import org.mapstruct.Mapper;
import ru.javlasov.springajax.dto.GenreDto;
import ru.javlasov.springajax.model.Genre;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GenreMapper {

    GenreDto entityToDto(Genre genre);

    List<GenreDto> entityToDto(List<Genre> genres);

}
