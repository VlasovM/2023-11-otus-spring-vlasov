package ru.javlasov.springacl.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.javlasov.springacl.exceptions.NotFoundException;
import ru.javlasov.springacl.repositories.UserRepository;
import ru.javlasov.springacl.services.UserService;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        var user = userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("Not found user with id = %s".formatted(username)));
        log.info("Authorization in the system: " + user.getUsername() + " - " + user.getRole());
        StringBuilder roles = new StringBuilder();
        user.getRole().forEach(role -> roles.append(" ").append(role.getRole()));
        return User
                .builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(roles.toString())
                .build();
    }

}
