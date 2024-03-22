package ru.javlasov.springwebflux.mappers;

import org.mapstruct.Mapper;
import ru.javlasov.springwebflux.dto.GenreDto;
import ru.javlasov.springwebflux.models.Genre;

@Mapper(componentModel = "spring")
public interface GenreMapper {

    GenreDto entityToDto(Genre genre);


}
