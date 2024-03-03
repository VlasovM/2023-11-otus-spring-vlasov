package ru.javlasov.springmvc.services;

import ru.javlasov.springmvc.dto.AuthorDto;

import java.util.List;

public interface AuthorService {

    List<AuthorDto> findAll();

}
