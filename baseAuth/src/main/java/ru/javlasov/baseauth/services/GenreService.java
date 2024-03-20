package ru.javlasov.baseauth.services;

import ru.javlasov.baseauth.dto.GenreDto;

import java.util.List;

public interface GenreService {

    List<GenreDto> findAll();

}
