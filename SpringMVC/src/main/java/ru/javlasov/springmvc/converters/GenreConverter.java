package ru.javlasov.springmvc.converters;

import org.springframework.stereotype.Component;
import ru.javlasov.springmvc.model.Genre;

@Component
public class GenreConverter {

    public String genreToString(Genre genre) {
        return "Id: %d, Name: %s".formatted(genre.getId(), genre.getName());
    }

}
