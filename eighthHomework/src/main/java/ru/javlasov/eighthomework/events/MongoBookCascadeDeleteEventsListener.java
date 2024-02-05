package ru.javlasov.eighthomework.events;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeDeleteEvent;
import org.springframework.stereotype.Component;
import ru.javlasov.eighthomework.models.Book;
import ru.javlasov.eighthomework.repositories.BookRepository;
import ru.javlasov.eighthomework.repositories.CommentRepository;

@Component
@RequiredArgsConstructor
public class MongoBookCascadeDeleteEventsListener extends AbstractMongoEventListener<Book> {

    private final CommentRepository commentRepository;

    private final BookRepository bookRepository;

    @Override
    public void onBeforeDelete(BeforeDeleteEvent<Book> event) {
        super.onBeforeDelete(event);
        var source = event.getSource();
        var id = source.get("_id").toString();
        var genreId = bookRepository.findById(id).orElseThrow().getGenre().getId();
        commentRepository.deleteById(genreId);
    }

}
