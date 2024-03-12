package ru.javlasov.springajax.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javlasov.springajax.dto.AuthorDto;
import ru.javlasov.springajax.services.AuthorService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping("author/")
    public List<AuthorDto> findAll() {
        return authorService.findAll();
    }

}
