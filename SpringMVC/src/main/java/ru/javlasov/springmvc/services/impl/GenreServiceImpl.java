package ru.javlasov.springmvc.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javlasov.springmvc.dto.GenreDto;
import ru.javlasov.springmvc.mappers.GenreMapper;
import ru.javlasov.springmvc.repositories.GenreRepository;
import ru.javlasov.springmvc.services.GenreService;

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
