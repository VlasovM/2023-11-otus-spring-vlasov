package ru.javlasov.springmvc.services;

import ru.javlasov.springmvc.model.Genre;

import java.util.List;

public interface GenreService {

    List<Genre> findAll();

}
