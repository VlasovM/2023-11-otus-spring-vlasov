package ru.javlasov.springajax.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javlasov.springajax.dto.GenreDto;
import ru.javlasov.springajax.services.GenreService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
public class GenreController {

    private final GenreService genreService;

    @GetMapping("genre/")
    public List<GenreDto> findAll() {
        return genreService.findAll();
    }

}
