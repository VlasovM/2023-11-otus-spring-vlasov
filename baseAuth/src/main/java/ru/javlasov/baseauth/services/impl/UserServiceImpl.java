package ru.javlasov.baseauth.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.javlasov.baseauth.exceptions.NotFoundException;
import ru.javlasov.baseauth.repositories.UserRepository;
import ru.javlasov.baseauth.services.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        var user = userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("Not found user with id = %s".formatted(username)));
        System.out.println(user.getUsername() + ": " + user.getPassword());
        return User
                .builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
    }

}
