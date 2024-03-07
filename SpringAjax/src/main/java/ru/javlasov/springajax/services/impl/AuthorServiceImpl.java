package ru.javlasov.springajax.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.javlasov.springajax.dto.AuthorDto;
import ru.javlasov.springajax.mappers.AuthorMapper;
import ru.javlasov.springajax.repositories.AuthorRepository;
import ru.javlasov.springajax.services.AuthorService;

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
