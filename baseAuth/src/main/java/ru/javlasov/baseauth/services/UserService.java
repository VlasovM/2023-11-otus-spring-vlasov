package ru.javlasov.baseauth.services;

import ru.javlasov.baseauth.model.User;

public interface UserService {

    //Временно возвращаю User (entity), потом поменяю на какой-нибудь UserDto;
    User getById();
}
