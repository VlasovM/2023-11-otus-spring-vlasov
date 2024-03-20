package ru.javlasov.baseauth.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javlasov.baseauth.dto.GenreDto;
import ru.javlasov.baseauth.mappers.GenreMapper;
import ru.javlasov.baseauth.repositories.GenreRepository;
import ru.javlasov.baseauth.services.GenreService;

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
