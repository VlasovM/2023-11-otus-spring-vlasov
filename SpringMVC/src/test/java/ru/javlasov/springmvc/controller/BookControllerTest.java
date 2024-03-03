package ru.javlasov.springmvc.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.javlasov.springmvc.dto.BookCreateDto;
import ru.javlasov.springmvc.dto.BookDto;
import ru.javlasov.springmvc.dto.BookUpdateDto;
import ru.javlasov.springmvc.model.Author;
import ru.javlasov.springmvc.model.Book;
import ru.javlasov.springmvc.model.Genre;
import ru.javlasov.springmvc.services.AuthorService;
import ru.javlasov.springmvc.services.BookService;
import ru.javlasov.springmvc.services.GenreService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService mockBookService;

    @MockBean
    private AuthorService mockAuthorService;

    @MockBean
    private GenreService mockGenreService;

    @Test
    @DisplayName("Should get all books")
    void shouldGetAllBooks() throws Exception {
        given(mockBookService.findAll()).willReturn(getAllBooks());
        var content = mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("homePage"))
                .andExpect(model().attributeExists("books"))
                .andReturn().getResponse().getContentAsString();

        assertTrue(content.contains(getAllBooks().get(0).getTitle()));
        assertTrue(content.contains(getAllBooks().get(1).getTitle()));
    }

    @Test
    @DisplayName("Should delete book")
    void shouldDeleteBook() throws Exception {
        this.mockMvc.perform(post("/delete")
                        .param("id", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));
    }

    @Test
    @DisplayName("Should redirect to edit view")
    void shouldRedirectToEditView() throws Exception {
        given(mockBookService.findById(1L)).willReturn(getBookUpdateDtoFromBook());
        var content = mockMvc.perform(get("/edit")
                        .param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("edit"))
                .andExpect(model().attributeExists("book"))
                .andReturn().getResponse().getContentAsString();
        assertTrue(content.contains(getAllBooks().get(0).getTitle()));
    }

    @Test
    @DisplayName("Should edit book")
    void shouldEditBook() throws Exception {
        this.mockMvc.perform(post("/edit")
                        .param("id", String.valueOf(getBookUpdateDtoFromBook().getId()))
                        .param("title", getBookUpdateDtoFromBook().getTitle())
                        .param("authorId", String.valueOf(getBookUpdateDtoFromBook().getAuthorId()))
                        .param("genreId", String.valueOf(getBookUpdateDtoFromBook().getGenreId())))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));
    }

    @Test
    @DisplayName("Should redirect to save view")
    void shouldRedirectToSaveView() throws Exception {
        this.mockMvc.perform(get("/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("create"))
                .andExpect(model().attributeExists("book"))
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    @DisplayName("Should save book")
    void shouldSaveBook() throws Exception {
        this.mockMvc.perform(post("/create")
                        .param("title", getCreatedBook().getTitle())
                        .param("authorId", String.valueOf(getCreatedBook().getAuthorId()))
                        .param("genreId", String.valueOf(getCreatedBook().getGenreId())))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));
    }

    private List<BookDto> getAllBooks() {
        Book bookFirst = new Book("Три товарища", getAllAuthors().get(0), getAllGenres().get(0));
        Book bookSecond = new Book("1984", getAllAuthors().get(1), getAllGenres().get(0));
        BookDto bookDtoFirst = new BookDto(bookFirst.getId(), bookFirst.getTitle(), bookFirst.getAuthor().getFullName(),
                bookFirst.getGenre().getName());
        BookDto bookDtoSecond = new BookDto(bookSecond.getId(), bookSecond.getTitle(), bookSecond.getAuthor().getFullName(),
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
        Book book = new Book("Три товарища", getAllAuthors().get(0), getAllGenres().get(0));
        return new BookUpdateDto(book.getId(), book.getTitle(), book.getAuthor().getId(), book.getGenre().getId());
    }

    private BookCreateDto getCreatedBook() {
        return new BookCreateDto(getAllBooks().get(0).getTitle(), getAllAuthors().get(0).getId(), getAllGenres().get(0).getId());
    }

}