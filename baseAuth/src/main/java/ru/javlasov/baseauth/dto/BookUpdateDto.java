package ru.javlasov.baseauth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookUpdateDto {

    @NotNull
    private Long id;

    @NotBlank(message = "Название книги не может быть пустым")
    @Size(min = 2, max = 30, message = "Допустимая длина книги от 2 до 20 символов")
    private String title;

    @NotNull
    private Long authorId;

    @NotNull
    private Long genreId;

}
