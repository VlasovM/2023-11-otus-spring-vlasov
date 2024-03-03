package ru.javlasov.springmvc.mappers;

import org.mapstruct.Mapper;
import ru.javlasov.springmvc.dto.GenreDto;
import ru.javlasov.springmvc.model.Genre;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GenreMapper {

    GenreDto entityToDto(Genre genre);

    List<GenreDto> entityToDto(List<Genre> genres);

}
