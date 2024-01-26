package ru.javlasov.seventhhomework.converters;

import org.springframework.stereotype.Component;
import ru.javlasov.seventhhomework.models.Genre;

@Component
public class GenreConverter {

    public String genreToString(Genre genre) {
        return "Id: %d, Name: %s".formatted(genre.getId(), genre.getName());
    }

}
