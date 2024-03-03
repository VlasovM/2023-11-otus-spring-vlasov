package ru.javlasov.springmvc.services;

import ru.javlasov.springmvc.dto.GenreDto;

import java.util.List;

public interface GenreService {

    List<GenreDto> findAll();

}
