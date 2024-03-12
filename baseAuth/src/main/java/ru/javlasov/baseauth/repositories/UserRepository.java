package ru.javlasov.baseauth.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.javlasov.baseauth.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
}
