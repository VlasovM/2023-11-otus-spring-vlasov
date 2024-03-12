package ru.javlasov.fifthhomework.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.javlasov.fifthhomework.models.Genre;
import ru.javlasov.fifthhomework.repositories.GenreRepository;
import ru.javlasov.fifthhomework.services.GenreService;

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
