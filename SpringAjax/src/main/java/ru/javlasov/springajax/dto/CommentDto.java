package ru.javlasov.springajax.dto;

import lombok.Data;

@Data
public class CommentDto {

    private Long id;

    private String text;

    private Long bookId;

}

