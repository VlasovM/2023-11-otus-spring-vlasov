package ru.javlasov.fifthhomework.converters;

import org.springframework.stereotype.Component;
import ru.javlasov.fifthhomework.models.Genre;

@Component
public class GenreConverter {

    public String genreToString(Genre genre) {
        return "Id: %d, Name: %s".formatted(genre.getId(), genre.getName());
    }

}
