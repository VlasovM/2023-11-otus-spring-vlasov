package ru.javlasov.eighthomework.converters;

import org.springframework.stereotype.Component;
import ru.javlasov.eighthomework.models.Author;

@Component
public class AuthorConverter {

    public String authorToString(Author author) {
        return "Id: %s, FullName: %s".formatted(author.getId(), author.getFullName());

    }
}
