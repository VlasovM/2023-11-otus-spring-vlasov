package ru.javlasov.springmvc.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.javlasov.springmvc.dto.BookCreateDto;
import ru.javlasov.springmvc.dto.BookDto;
import ru.javlasov.springmvc.dto.BookUpdateDto;
import ru.javlasov.springmvc.services.AuthorService;
import ru.javlasov.springmvc.services.BookService;
import ru.javlasov.springmvc.services.GenreService;

import java.util.List;

@Controller
@RequiredArgsConstructor
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
        bookService.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String editBook(@RequestParam("id") Long id, Model model) {
        var bookUpdateDto = bookService.findById(id);
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
        bookService.update(book.getId(), book.getTitle(), book.getAuthorId(), book.getGenreId());
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
        bookService.create(book.getTitle(), book.getAuthorId(), book.getGenreId());
        return "redirect:/";
    }

}
