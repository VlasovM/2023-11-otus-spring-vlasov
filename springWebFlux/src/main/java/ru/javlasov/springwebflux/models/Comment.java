package ru.javlasov.springwebflux.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document("comments")
public class Comment {

    @Id
    private long id;

    private String text;

    @DBRef(lazy = true)
    private Book book;

    public Comment(String text, Book book) {
        this.text = text;
        this.book = book;
    }

}
