package ru.javlasov.springwebflux.services;

import reactor.core.publisher.Flux;
import ru.javlasov.springwebflux.dto.GenreDto;

public interface GenreService {

    Flux<GenreDto> findAll();

}
