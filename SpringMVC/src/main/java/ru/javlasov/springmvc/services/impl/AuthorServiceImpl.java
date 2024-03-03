package ru.javlasov.springmvc.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.javlasov.springmvc.dto.AuthorDto;
import ru.javlasov.springmvc.mappers.AuthorMapper;
import ru.javlasov.springmvc.repositories.AuthorRepository;
import ru.javlasov.springmvc.services.AuthorService;

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
