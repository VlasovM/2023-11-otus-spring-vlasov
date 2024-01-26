package ru.javlasov.seventhhomework.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.javlasov.seventhhomework.models.Comment;

@Component
@RequiredArgsConstructor
public class CommentConverter {

    private final BookConverter bookConverter;

    public String commentToString(Comment comment) {
        return "Id: %d, text: %s, book: {%s}".formatted(
                comment.getId(),
                comment.getText(),
                bookConverter.bookToString(comment.getBook())
        );
    }

}
