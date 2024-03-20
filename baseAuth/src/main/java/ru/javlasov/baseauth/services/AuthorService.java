package ru.javlasov.baseauth.services;

import ru.javlasov.baseauth.dto.AuthorDto;

import java.util.List;

public interface AuthorService {

    List<AuthorDto> findAll();

}
