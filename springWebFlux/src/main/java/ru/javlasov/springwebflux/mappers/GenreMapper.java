package ru.javlasov.springwebflux.mappers;

import org.mapstruct.Mapper;
import ru.javlasov.springwebflux.dto.GenreDto;
import ru.javlasov.springwebflux.models.Genre;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GenreMapper {

    GenreDto entityToDto(Genre genre);

    List<GenreDto> entityToDto(List<Genre> genres);


}
