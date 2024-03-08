package ru.javlasov.springajax.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.javlasov.springajax.dto.BookCreateDto;
import ru.javlasov.springajax.dto.BookDto;
import ru.javlasov.springajax.dto.BookUpdateDto;
import ru.javlasov.springajax.model.Author;
import ru.javlasov.springajax.model.Book;
import ru.javlasov.springajax.model.Genre;
import ru.javlasov.springajax.services.AuthorService;
import ru.javlasov.springajax.services.BookService;
import ru.javlasov.springajax.services.GenreService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BookService mockBookService;

    @MockBean
    private AuthorService mockAuthorService;

    @MockBean
    private GenreService mockGenreService;


    @Test
    @DisplayName("Should get all books")
    void getAllBooksTest() throws Exception {
        given(mockBookService.findAll()).willReturn(getAllBooks());
        mockMvc.perform(get("/api/v1/book/").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(getAllBooks())));
    }

    @Test
    @DisplayName("Should find book by id")
    void getBookByIdTest() throws Exception {
        var expectedBook = getBookUpdateDtoFromBook();
        mockMvc.perform(put("/api/v1/book/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(expectedBook)))
                .andExpect(status().isOk());
        verify(mockBookService).update(expectedBook);
    }

    @Test
    @DisplayName("Should delete book by id")
    void deleteBookByIdTest() throws Exception {
        var deletedBookId = 1L;
        mockMvc.perform(delete("/api/v1/book/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(deletedBookId)))
                .andExpect(status().isOk());
        verify(mockBookService).deleteById(deletedBookId);
        assertThat(mockBookService.findById(deletedBookId)).isNull();
    }

    @Test
    @DisplayName("Should create new book")
    void createNewBookTest() throws Exception {
        var content = getCreatedBook();
        mockMvc.perform(post("/api/v1/book/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(content)))
                .andExpect(status().is2xxSuccessful());
    }

    private List<BookDto> getAllBooks() {
        var bookFirst = new Book("Три товарища", getAllAuthors().get(0), getAllGenres().get(0));
        var bookSecond = new Book("1984", getAllAuthors().get(1), getAllGenres().get(0));
        var bookDtoFirst = new BookDto(bookFirst.getId(), bookFirst.getTitle(), bookFirst.getAuthor().getFullName(),
                bookFirst.getGenre().getName());
        var bookDtoSecond = new BookDto(bookSecond.getId(), bookSecond.getTitle(), bookSecond.getAuthor().getFullName(),
                bookSecond.getGenre().getName());
        return List.of(bookDtoFirst, bookDtoSecond);
    }

    private List<Author> getAllAuthors() {
        return List.of(new Author(1, "Эрих Мария Ремарк"), new Author(2, "Джордж Оруэлл"));
    }

    private List<Genre> getAllGenres() {
        return List.of(new Genre(1, "Роман"), new Genre(2, "Антиутопия"));
    }

    private BookUpdateDto getBookUpdateDtoFromBook() {
        var book = new Book("Три товарища", getAllAuthors().get(0), getAllGenres().get(0));
        return new BookUpdateDto(1L, book.getTitle(), book.getAuthor().getId(), book.getGenre().getId());
    }

    private BookCreateDto getCreatedBook() {
        return new BookCreateDto(getAllBooks().get(0).getTitle(), getAllAuthors().get(0).getId(), getAllGenres().get(0).getId());
    }

}