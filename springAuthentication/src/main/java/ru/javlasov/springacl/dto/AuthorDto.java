package ru.javlasov.springacl.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthorDto {

    private Long id;

    private String fullName;

}
