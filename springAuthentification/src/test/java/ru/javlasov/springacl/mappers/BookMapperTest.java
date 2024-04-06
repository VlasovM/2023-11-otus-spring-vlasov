package ru.javlasov.springacl.mappers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.javlasov.springacl.dto.AuthorDto;
import ru.javlasov.springacl.dto.BookDto;
import ru.javlasov.springacl.dto.GenreDto;
import ru.javlasov.springacl.model.Author;
import ru.javlasov.springacl.model.Book;
import ru.javlasov.springacl.model.Genre;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class BookMapperTest {

    @Autowired
    private BookMapper mapperUnderTest;

    @Test
    @DisplayName("Test convert book -> BookDto")
    void modelToDtoTest() {
        //given
        var model = new Book(1L, "Title", new Author(1L, "Author"), new Genre(1L, "Genre"));

        //when
        var dto = mapperUnderTest.modelToDto(model);

        //then
        Assertions.assertNotNull(dto);
        Assertions.assertEquals(model.getId(), dto.getId());
        Assertions.assertEquals(model.getTitle(), dto.getTitle());
        Assertions.assertEquals(model.getAuthor().getFullName(), dto.getAuthor().getFullName());
        Assertions.assertEquals(model.getGenre().getName(), dto.getGenre().getName());
    }

    @Test
    @DisplayName("Test convert bookDto -> book")
    void dtoToModel() {
        //given
        var dto = new BookDto(1L, "title", new AuthorDto(1L, "Author"),
                new GenreDto(1L, "Genre"));

        //when
        var model = mapperUnderTest.dtoToModel(dto);

        //then
        Assertions.assertNotNull(dto);
        Assertions.assertEquals(dto.getId(), model.getId());
        Assertions.assertEquals(dto.getTitle(), model.getTitle());
        Assertions.assertEquals(dto.getAuthor().getFullName(), model.getAuthor().getFullName());
        Assertions.assertEquals(dto.getGenre().getName(), model.getGenre().getName());
    }

    @Test
    @DisplayName("Test convert list book -> list dto")
    void modelToDtoList() {
        List<Book> modelList = new ArrayList<>();
        var model = new Book(1L, "Title", new Author(1L, "Author"), new Genre(1L, "Genre"));
        modelList.add(model);

        var dto = mapperUnderTest.modelToDtoList(modelList);

        //then
        Assertions.assertNotNull(dto);
        Assertions.assertEquals(modelList.size(), dto.size());
    }
}