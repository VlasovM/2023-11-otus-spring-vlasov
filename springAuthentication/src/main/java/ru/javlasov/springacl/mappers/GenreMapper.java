package ru.javlasov.springacl.mappers;

import org.mapstruct.Mapper;
import ru.javlasov.springacl.dto.GenreDto;
import ru.javlasov.springacl.model.Genre;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GenreMapper {

    GenreDto entityToDto(Genre genre);

    Genre dtoToModel(GenreDto genreDto);

    List<GenreDto> entityToDto(List<Genre> genres);

}
