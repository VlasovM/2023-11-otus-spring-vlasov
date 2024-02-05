package ru.javlasov.eighthomework.commands;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.javlasov.eighthomework.converters.CommentConverter;
import ru.javlasov.eighthomework.services.CommentService;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@ShellComponent
public class CommentCommands {

    private final CommentService commentService;

    private final CommentConverter commentConverter;

    @ShellMethod(value = "Find comment by id", key = "cbid")
    public String findCommentById(@ShellOption("id") String id) {
        return commentService.findById(id)
                .map(commentConverter::commentToString)
                .orElse("Comment by id = %d not found".formatted(id));
    }

    @ShellMethod(value = "Find comments by bookId", key = "cbbid")
    public String findCommentByBookId(@ShellOption("bookId") String bookId) {
        return commentService.findCommentsByBookId(bookId).stream()
                .map(commentConverter::commentToString)
                .collect(Collectors.joining("," + System.lineSeparator()));
    }

    @ShellMethod(value = "Insert comment", key = "cins")
    public String insertComment(@ShellOption("text") String text, @ShellOption("bookId") String bookId) {
        var comment = commentService.create(text, bookId);
        return commentConverter.commentToString(comment);
    }

    @ShellMethod(value = "Update comment", key = "cupd")
    public String updateComment(@ShellOption("id") String id, @ShellOption("text") String text,
                                @ShellOption("bookId") String bookId) {
        var comment = commentService.update(id, text, bookId);
        return commentConverter.commentToString(comment);
    }

    @ShellMethod(value = "Delete comment", key = "cdel")
    public void deleteComment(@ShellOption("id") String id) {
        commentService.deleteById(id);
    }

}
