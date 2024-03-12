package ru.javlasov.springajax.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javlasov.springajax.dto.GenreDto;
import ru.javlasov.springajax.mappers.GenreMapper;
import ru.javlasov.springajax.repositories.GenreRepository;
import ru.javlasov.springajax.services.GenreService;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    private final GenreMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public List<GenreDto> findAll() {
        return mapper.entityToDto(genreRepository.findAll());
    }

}
