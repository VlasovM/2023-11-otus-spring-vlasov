package ru.javlasov.springmvc.converters;

import org.springframework.stereotype.Component;
import ru.javlasov.springmvc.model.Author;

@Component
public class AuthorConverter {

    public String authorToString(Author author) {
        return "Id: %d, FullName: %s".formatted(author.getId(), author.getFullName());

    }
}
