package ru.javlasov.springajax.services;

import ru.javlasov.springajax.dto.AuthorDto;

import java.util.List;

public interface AuthorService {

    List<AuthorDto> findAll();

}
