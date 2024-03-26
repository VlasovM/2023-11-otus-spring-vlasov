package ru.javlasov.springwebflux.mongock;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import reactor.core.publisher.Mono;
import ru.javlasov.springwebflux.models.Author;
import ru.javlasov.springwebflux.models.Book;
import ru.javlasov.springwebflux.models.Genre;
import ru.javlasov.springwebflux.repositories.AuthorRepository;
import ru.javlasov.springwebflux.repositories.BookRepository;
import ru.javlasov.springwebflux.repositories.GenreRepository;

import java.util.ArrayList;
import java.util.List;

@ChangeLog(order = "001")
public class DatabaseChangelog {

    private List<Mono<Author>> authors = new ArrayList<>();

    private List<Mono<Genre>> genres = new ArrayList<>();

    private List<Mono<Book>> books = new ArrayList<>();

    @ChangeSet(order = "000", id = "dropDb", author = "jaVlasov", runAlways = true)
    public void dropDb(MongoDatabase db) {
        db.drop();
    }

    @ChangeSet(order = "001", id = "initAuthors", author = "jaVlasov", runAlways = true)
    public void initAuthors(AuthorRepository authorRepository) {
        authors.add(authorRepository.save(new Author("Fedor Dostoevsky")));
        authors.add(authorRepository.save(new Author("Alexander Pushkin")));
    }

    @ChangeSet(order = "002", id = "initGenres", author = "jaVlasov", runAlways = true)
    public void initGenres(GenreRepository genreRepository) {
        genres.add(genreRepository.save(new Genre("Novel")));
    }


    @ChangeSet(order = "003", id = "initBooks", author = "jaVlasov", runAlways = true)
    public void initBooks(BookRepository bookRepository) {
        books.add(bookRepository.save(new Book("Captain's daughter", authors.get(0).block(), genres.get(0).block())));
        books.add(bookRepository.save(new Book("Idiot", authors.get(1).block(), genres.get(0).block())));
        books.add(bookRepository.save(new Book("Demons", authors.get(1).block(), genres.get(0).block())));
    }

}
