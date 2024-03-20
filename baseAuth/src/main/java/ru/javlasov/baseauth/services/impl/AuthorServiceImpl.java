package ru.javlasov.baseauth.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.javlasov.baseauth.dto.AuthorDto;
import ru.javlasov.baseauth.mappers.AuthorMapper;
import ru.javlasov.baseauth.repositories.AuthorRepository;
import ru.javlasov.baseauth.services.AuthorService;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    private final AuthorMapper mapper;

    @Override
    public List<AuthorDto> findAll() {
        return mapper.entityToDto(authorRepository.findAll());
    }

}
