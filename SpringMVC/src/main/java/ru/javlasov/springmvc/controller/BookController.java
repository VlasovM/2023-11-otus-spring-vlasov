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
import ru.javlasov.springmvc.dto.BookDto;
import ru.javlasov.springmvc.model.Book;
import ru.javlasov.springmvc.services.BookService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/")
    public String getAllBooks(Model model) {
        List<Book> allBooks = bookService.findAll();
        model.addAttribute("books", allBooks);
        return "homePage";
    }

    @PostMapping("/delete")
    public String deleteBook(@RequestParam("id") long id) {
        bookService.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String editBook(@RequestParam("id") long id, Model model) {
        var bookDto = bookService.findById(id);
        model.addAttribute("book", bookDto);
        return "edit";
    }

    @PostMapping("/edit")
    public String editBook(@Valid @ModelAttribute("book") BookDto book,
                           BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "edit";
        }
        bookService.update(book.getId(), book.getTitle(), book.getAuthorId(), book.getGenreId());
        return "redirect:/";
    }

    @GetMapping("/create")
    public String addBook(Model model) {
        model.addAttribute("book", new BookDto(0, "", 0, 0));
        return "create";
    }

    @PostMapping("/create")
    public String addBook(@Valid @ModelAttribute("book") BookDto book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "create";
        }
        bookService.create(book.getTitle(), book.getAuthorId(), book.getGenreId());
        return "redirect:/";
    }

}
