package ru.javlasov.eighthomework.services;

import ru.javlasov.eighthomework.models.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    List<Author> findAll();

    Optional<Author> findById(String id);

}
