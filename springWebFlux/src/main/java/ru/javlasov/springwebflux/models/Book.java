package ru.javlasov.springwebflux.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@Document("books")
public class Book {

    @Id
    private String id;

    @Field(name = "title")
    private String title;

    @Field(name = "author")
    private Author author;


    @Field(name = "genre")
    private Genre genre;

    public Book(String title, Author author, Genre genre) {
        this.title = title;
        this.author = author;
        this.genre = genre;
    }

}
