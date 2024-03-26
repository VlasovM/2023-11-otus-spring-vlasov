package ru.javlasov.springwebflux.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ru.javlasov.springwebflux.dto.GenreDto;
import ru.javlasov.springwebflux.services.GenreService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
public class GenreController {

    private final GenreService genreService;

    @GetMapping("/genres")
    public Flux<GenreDto> getGenres() {
        return genreService.findAll();
    }

}
