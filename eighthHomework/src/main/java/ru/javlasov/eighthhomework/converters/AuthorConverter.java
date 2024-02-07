package ru.javlasov.eighthhomework.converters;

import org.springframework.stereotype.Component;
import ru.javlasov.eighthhomework.models.Author;

@Component
public class AuthorConverter {

    public String authorToString(Author author) {
        return "Id: %s, FullName: %s".formatted(author.getId(), author.getFullName());

    }
}
