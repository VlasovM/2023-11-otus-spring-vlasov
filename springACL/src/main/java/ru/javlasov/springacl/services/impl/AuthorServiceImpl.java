package ru.javlasov.springacl.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.javlasov.springacl.dto.AuthorDto;
import ru.javlasov.springacl.mappers.AuthorMapper;
import ru.javlasov.springacl.repositories.AuthorRepository;
import ru.javlasov.springacl.services.AuthorService;

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
