package ru.javlasov.springwebflux.services;

import reactor.core.publisher.Flux;
import ru.javlasov.springwebflux.dto.AuthorDto;

public interface AuthorService {

    Flux<AuthorDto> findAll();

}
