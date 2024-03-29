package ru.javlasov.baseauth.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.javlasov.baseauth.model.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByUsername(String username);

}
