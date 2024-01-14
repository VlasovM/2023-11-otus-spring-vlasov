package ru.javlasov.sixthhomework.converters;

import org.springframework.stereotype.Component;
import ru.javlasov.sixthhomework.models.Genre;

@Component
public class GenreConverter {

    public String genreToString(Genre genre) {
        return "Id: %d, Name: %s".formatted(genre.getId(), genre.getName());
    }

}
