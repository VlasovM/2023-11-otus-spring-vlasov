package ru.javlasov.springacl.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.javlasov.springacl.dto.BookCreateDto;
import ru.javlasov.springacl.dto.BookDto;
import ru.javlasov.springacl.dto.BookUpdateDto;
import ru.javlasov.springacl.services.AuthorService;
import ru.javlasov.springacl.services.BookService;
import ru.javlasov.springacl.services.GenreService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BookController {

    private final BookService bookService;

    private final AuthorService authorService;

    private final GenreService genreService;

    @GetMapping("/")
    public String getAllBooks(Model model) {
        List<BookDto> allBooks = bookService.findAll();
        model.addAttribute("books", allBooks);
        return "homePage";
    }

    @PostMapping("/delete")
    public String deleteBook(@RequestParam("id") Long id) {
        var currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        log.info("Book id = %d has been deleted by: %s".formatted(id, currentUsername));
        bookService.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String editBook(@RequestParam("id") Long id, Model model) {
        var bookUpdateDto = bookService.findByIdByUpdate(id);
        var authors = authorService.findAll();
        var genres = genreService.findAll();
        model.addAttribute("book", bookUpdateDto);
        model.addAttribute("authors", authors);
        model.addAttribute("genres", genres);
        return "edit";
    }

    @PostMapping("/edit")
    public String editBook(@Valid @ModelAttribute("book") BookUpdateDto book,
                           BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("id", book.getId());
            model.addAttribute("authors", authorService.findAll());
            model.addAttribute("genres", genreService.findAll());
            return "edit";
        }
        var currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        log.info("Book id = %d has been edited by user: %s".formatted(book.getId(), currentUsername));
        bookService.update(book);
        return "redirect:/";
    }

    @GetMapping("/create")
    public String addBook(Model model) {
        var authors = authorService.findAll();
        var genres = genreService.findAll();
        model.addAttribute("authors", authors);
        model.addAttribute("genres", genres);
        model.addAttribute("book", new BookCreateDto("", null, null));
        return "create";
    }

    @PostMapping("/create")
    public String addBook(@Valid @ModelAttribute("book") BookCreateDto book, BindingResult bindingResult,
                          Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("authors", authorService.findAll());
            model.addAttribute("genres", genreService.findAll());
            return "create";
        }
        var currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        log.info("Create new book %s by user: %s".formatted(book.getTitle(), currentUsername));
        bookService.create(book);
        return "redirect:/";
    }

}
