package ru.javlasov.springmvc.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookViewDto {

    private Long id;

    private String title;

    private String author;

    private String genre;

}
