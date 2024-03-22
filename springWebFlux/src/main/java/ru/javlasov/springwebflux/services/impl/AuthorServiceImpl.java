package ru.javlasov.springwebflux.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.javlasov.springwebflux.dto.AuthorDto;
import ru.javlasov.springwebflux.exceptions.NotFoundException;
import ru.javlasov.springwebflux.mappers.AuthorMapper;
import ru.javlasov.springwebflux.repositories.AuthorRepository;
import ru.javlasov.springwebflux.services.AuthorService;

@RequiredArgsConstructor
@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    private final AuthorMapper mapper;

    @Override
    public Flux<AuthorDto> findAll() {
        return authorRepository.findAll().map(mapper::entityToDto);
    }

    @Override
    public Mono<AuthorDto> findById(String id) {
        return authorRepository.findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException("Not fount Author with id = %s".formatted(id))))
                .map(mapper::entityToDto);
    }

}
