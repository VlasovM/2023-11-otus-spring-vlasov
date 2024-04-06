package ru.javlasov.springacl.services;

import ru.javlasov.springacl.dto.AuthorDto;

import java.util.List;

public interface AuthorService {

    List<AuthorDto> findAll();

}
