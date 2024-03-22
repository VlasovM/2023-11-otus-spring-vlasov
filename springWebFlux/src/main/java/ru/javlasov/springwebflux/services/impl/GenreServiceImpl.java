package ru.javlasov.springwebflux.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import ru.javlasov.springwebflux.dto.GenreDto;
import ru.javlasov.springwebflux.mappers.GenreMapper;
import ru.javlasov.springwebflux.repositories.GenreRepository;
import ru.javlasov.springwebflux.services.GenreService;

@RequiredArgsConstructor
@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    private final GenreMapper mapper;

    @Override
    public Flux<GenreDto> findAll() {
        return genreRepository.findAll().map(mapper::entityToDto);
    }

}
