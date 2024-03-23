package ru.javlasov.springacl.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javlasov.springacl.dto.GenreDto;
import ru.javlasov.springacl.mappers.GenreMapper;
import ru.javlasov.springacl.repositories.GenreRepository;
import ru.javlasov.springacl.services.GenreService;

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
