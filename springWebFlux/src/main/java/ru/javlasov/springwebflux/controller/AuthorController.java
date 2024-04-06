package ru.javlasov.springwebflux.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ru.javlasov.springwebflux.dto.AuthorDto;
import ru.javlasov.springwebflux.services.AuthorService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping("/authors")
    public Flux<AuthorDto> getAuthors() {
        return authorService.findAll();
    }

}
