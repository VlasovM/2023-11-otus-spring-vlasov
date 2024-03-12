package ru.javlasov.springajax.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.javlasov.springajax.dto.CommentDto;
import ru.javlasov.springajax.model.Comment;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(source = "source.book.id", target = "bookId")
    CommentDto entityToDto(Comment source);

    List<CommentDto> entityToDtoList(List<Comment> source);

}
