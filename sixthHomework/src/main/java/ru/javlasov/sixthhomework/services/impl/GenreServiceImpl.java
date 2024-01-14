package ru.javlasov.sixthhomework.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.javlasov.sixthhomework.models.Genre;
import ru.javlasov.sixthhomework.repositories.GenreRepository;
import ru.javlasov.sixthhomework.services.GenreService;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Override
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

}
