package ru.javlasov.baseauth.mappers;

import org.mapstruct.Mapper;
import ru.javlasov.baseauth.dto.GenreDto;
import ru.javlasov.baseauth.model.Genre;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GenreMapper {

    GenreDto entityToDto(Genre genre);

    List<GenreDto> entityToDto(List<Genre> genres);

}
