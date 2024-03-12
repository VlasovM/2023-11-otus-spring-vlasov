package ru.javlasov.baseauth.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.javlasov.baseauth.exceptions.EntityNotFoundException;
import ru.javlasov.baseauth.model.User;
import ru.javlasov.baseauth.repositories.UserRepository;

@Service
@RequiredArgsConstructor
public class UserServiceImpl {

    private final UserRepository userRepository;

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Not found user with id = %d", id)));
    }

}
