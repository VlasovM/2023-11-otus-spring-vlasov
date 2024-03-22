package ru.javlasov.springwebflux.services;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.javlasov.springwebflux.dto.AuthorDto;

public interface AuthorService {

    Flux<AuthorDto> findAll();

    Mono<AuthorDto> findById(String id);

}
