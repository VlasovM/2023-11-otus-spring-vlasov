package ru.javlasov.sixthhomework.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javlasov.sixthhomework.models.Author;
import ru.javlasov.sixthhomework.repositories.AuthorRepository;
import ru.javlasov.sixthhomework.services.AuthorService;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Author> findById(long id) {
        return authorRepository.findById(id);
    }

}
