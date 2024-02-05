package ru.javlasov.eighthhomework.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.javlasov.eighthhomework.models.Comment;

@Component
@RequiredArgsConstructor
public class CommentConverter {

    private final BookConverter bookConverter;

    public String commentToString(Comment comment) {
        return "Id: %s, text: %s, book: {%s}".formatted(
                comment.getId(),
                comment.getText(),
                bookConverter.bookToString(comment.getBook())
        );
    }

}
