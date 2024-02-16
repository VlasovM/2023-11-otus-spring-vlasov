package ru.javlasov.springmvc.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.javlasov.springmvc.dto.BookDto;
import ru.javlasov.springmvc.model.Author;
import ru.javlasov.springmvc.model.Book;
import ru.javlasov.springmvc.model.Genre;
import ru.javlasov.springmvc.services.BookService;

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
        given(mockBookService.findById(1)).willReturn(getBookDtoFromBook(getAllBooks().get(0)));
        var content = mockMvc.perform(get("/edit")
                        .param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("edit"))
                .andExpect(model().attributeExists("book"))
                .andReturn().getResponse().getContentAsString();
        assertTrue(content.contains(getBookDtoFromBook(getAllBooks().get(0)).getTitle()));
    }

    @Test
    @DisplayName("Should edit book")
    void shouldEditBook() throws Exception {
        this.mockMvc.perform(post("/edit")
                        .param("title", getAllBooks().get(0).getTitle())
                        .param("id", String.valueOf(getAllBooks().get(0).getId()))
                        .param("authorId", String.valueOf(getAllBooks().get(0).getAuthor().getId()))
                        .param("genreId", String.valueOf(getAllBooks().get(0).getGenre().getId())))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));
    }

    @Test
    @DisplayName("Should redirect to save view")
    void shouldRedirectToSaveView() throws Exception {
        var content = mockMvc.perform(get("/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("create"))
                .andExpect(model().attributeExists("book"))
                .andReturn().getResponse().getContentAsString();
        assertTrue(content.contains(new BookDto(0, "", 0, 0).getTitle()));
    }

    @Test
    @DisplayName("Should save book")
    void shouldSaveBook() throws Exception {
        this.mockMvc.perform(post("/create")
                        .param("title", getAllBooks().get(0).getTitle())
                        .param("id", String.valueOf(getAllBooks().get(0).getId()))
                        .param("authorId", String.valueOf(getAllBooks().get(0).getAuthor().getId()))
                        .param("genreId", String.valueOf(getAllBooks().get(0).getGenre().getId())))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));
    }

    private List<Book> getAllBooks() {
        return List.of(new Book("Три товарища", getAllAuthors().get(0), getAllGenres().get(0)),
                new Book("1984", getAllAuthors().get(1), getAllGenres().get(0)));
    }

    private List<Author> getAllAuthors() {
        return List.of(new Author(1, "Эрих Мария Ремарк"), new Author(2, "Джордж Оруэлл"));
    }

    private List<Genre> getAllGenres() {
        return List.of(new Genre(1, "Роман"), new Genre(2, "Антиутопия"));
    }

    private BookDto getBookDtoFromBook(Book book) {
        return new BookDto(book.getId(), book.getTitle(), book.getAuthor().getId(), book.getGenre().getId());
    }

}