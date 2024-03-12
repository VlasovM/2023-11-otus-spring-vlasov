package ru.javlasov.eighthhomework.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import ru.javlasov.eighthhomework.models.Author;
import ru.javlasov.eighthhomework.models.Book;
import ru.javlasov.eighthhomework.models.Genre;
import ru.javlasov.eighthhomework.repositories.AuthorRepository;
import ru.javlasov.eighthhomework.repositories.BookRepository;
import ru.javlasov.eighthhomework.repositories.GenreRepository;

@ChangeLog
public class DatabaseChangelog {

    @ChangeSet(order = "001", id = "dropDb", author = "jaVlasov", runAlways = true)
    public void dropDb(MongoDatabase db) {
        db.drop();
    }

    @ChangeSet(order = "002", id = "insertLermontov", author = "jaVlasov")
    public void insertLermontov(MongoDatabase db) {
        MongoCollection<Document> myCollection = db.getCollection("authors");
        var doc = new Document().append("_id", "1").append("fullName", "Mikhail Lermontov");
        myCollection.insertOne(doc);
    }

    @ChangeSet(order = "003", id = "insertGogol", author = "jaVlasov")
    public void insertPushkin(AuthorRepository repository) {
        repository.save(new Author("2", "Nikolay Gogol"));
    }

    @ChangeSet(order = "004", id = "insertGenreNovel", author = "jaVlasov")
    public void insertGenreNovel(GenreRepository genreRepository) {
        genreRepository.save(new Genre("1", "Novel"));
    }

    @ChangeSet(order = "005", id = "insertBookByGogol", author = "jaVlasov")
    public void insertBookByGogol(BookRepository bookRepository, AuthorRepository authorRepository,
                                    GenreRepository genreRepository) {
        var author = authorRepository.findById("2").orElseThrow();
        var genre = genreRepository.findById("1").orElseThrow();
        bookRepository.save(new Book("1", "Dead souls", author, genre));
    }

    @ChangeSet(order = "006", id = "insertBookByLermontov", author = "jaVlasov")
    public void insertBookByLermontov(BookRepository bookRepository, AuthorRepository authorRepository,
                                    GenreRepository genreRepository) {
        var author = authorRepository.findById("1").orElseThrow();
        var genre = genreRepository.findById("1").orElseThrow();
        bookRepository.save(new Book("2", "Heroes of our time", author, genre));
    }

}
