package ru.javlasov.baseauth.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.javlasov.baseauth.dto.BookCreateDto;
import ru.javlasov.baseauth.dto.BookDto;
import ru.javlasov.baseauth.dto.BookUpdateDto;
import ru.javlasov.baseauth.exceptions.NotFoundException;
import ru.javlasov.baseauth.model.Author;
import ru.javlasov.baseauth.model.Book;
import ru.javlasov.baseauth.model.Genre;
import ru.javlasov.baseauth.security.SecurityConfig;
import ru.javlasov.baseauth.services.AuthorService;
import ru.javlasov.baseauth.services.BookService;
import ru.javlasov.baseauth.services.GenreService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(BookController.class)
@Import(SecurityConfig.class)
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
        var content = mockMvc.perform(get("/").with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("homePage"))
                .andExpect(model().attributeExists("books"))
                .andReturn().getResponse().getContentAsString();

        assertTrue(content.contains(getAllBooks().get(0).getTitle()));
        assertTrue(content.contains(getAllBooks().get(1).getTitle()));
    }

    @Test
    @DisplayName("Should delete book")
    @WithMockUser(username = "user", roles = "USER")
    void shouldDeleteBook() throws Exception {
        this.mockMvc.perform(post("/delete")
                        .param("id", "1")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));
    }

    @Test
    @DisplayName("Should redirect to edit view")
    @WithMockUser(username = "user", roles = "USER")
    void shouldRedirectToEditView() throws Exception {
        given(mockBookService.findByIdByUpdate(1L)).willReturn(getBookUpdateDtoFromBook());
        var content = mockMvc.perform(get("/edit")
                        .param("id", "1")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("edit"))
                .andExpect(model().attributeExists("book"))
                .andReturn().getResponse().getContentAsString();
        assertTrue(content.contains(getAllBooks().get(0).getTitle()));
    }

    @Test
    @DisplayName("Should edit book")
    @WithMockUser(username = "user", roles = "USER")
    void shouldEditBook() throws Exception {
        this.mockMvc.perform(post("/edit")
                        .param("id", String.valueOf(getBookUpdateDtoFromBook().getId()))
                        .param("title", getBookUpdateDtoFromBook().getTitle())
                        .param("authorId", String.valueOf(getBookUpdateDtoFromBook().getAuthorId()))
                        .param("genreId", String.valueOf(getBookUpdateDtoFromBook().getGenreId()))
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));
    }

    @Test
    @DisplayName("Should redirect to save view")
    @WithMockUser(username = "user", roles = "USER")
    void shouldRedirectToSaveView() throws Exception {
        this.mockMvc.perform(get("/create")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("create"))
                .andExpect(model().attributeExists("book"))
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    @DisplayName("Should save book")
    @WithMockUser(username = "user", roles = "USER")
    void shouldSaveBook() throws Exception {
        this.mockMvc.perform(post("/create")
                        .param("title", getCreatedBook().getTitle())
                        .param("authorId", String.valueOf(getCreatedBook().getAuthorId()))
                        .param("genreId", String.valueOf(getCreatedBook().getGenreId()))
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));
    }

    @Test
    @DisplayName("Should find not found entity and get 404 code")
    @WithMockUser(username = "user", roles = "USER")
    void notFountEntityTest() throws Exception {
        given(mockBookService.findByIdByUpdate(1L)).willThrow(new NotFoundException("Not found book with id = %d".formatted(1)));
        mockMvc.perform(get("/edit")
                        .param("id", "1")
                        .with(csrf()))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Should get 401 (Unauthorized) error code")
    void unauthorizedTest() throws Exception {
        given(mockBookService.findByIdByUpdate(1L)).willThrow(new NotFoundException("Not found book with id = %d".formatted(1)));
        mockMvc.perform(get("/edit")
                        .param("id", "1")
                        .with(csrf()))
                .andExpect(status().isUnauthorized());
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