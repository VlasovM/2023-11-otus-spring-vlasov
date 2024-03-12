package ru.javlasov.springajax.services;

import ru.javlasov.springajax.dto.GenreDto;

import java.util.List;

public interface GenreService {

    List<GenreDto> findAll();

}
