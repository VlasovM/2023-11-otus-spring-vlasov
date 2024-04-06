package ru.javlasov.springacl.services;

import ru.javlasov.springacl.dto.GenreDto;

import java.util.List;

public interface GenreService {

    List<GenreDto> findAll();

}
