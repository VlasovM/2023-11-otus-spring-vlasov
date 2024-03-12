package ru.javlasov.springajax.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.javlasov.springajax.dto.AuthorDto;
import ru.javlasov.springajax.services.AuthorService;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthorController.class)
public class AuthorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AuthorService authorService;

    @Test
    @DisplayName("Should get all authors")
    void getAllGenresTest() throws Exception {
        given(authorService.findAll()).willReturn(getAllAuthors());
        mockMvc.perform(get("/api/v1/author/").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(getAllAuthors())));
    }

    private List<AuthorDto> getAllAuthors() {
        var authorFirst = new AuthorDto(1L, "Эрих Мария Ремарк");
        var authorSecond = new AuthorDto(2L, "Артур Хэйли");
        return List.of(authorFirst, authorSecond);
    }

}