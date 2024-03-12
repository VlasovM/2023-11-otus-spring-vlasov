package ru.javlasov.seventhhomework.converters;

import org.springframework.stereotype.Component;
import ru.javlasov.seventhhomework.models.Author;

@Component
public class AuthorConverter {

    public String authorToString(Author author) {
        return "Id: %d, FullName: %s".formatted(author.getId(), author.getFullName());

    }
}
