package ru.javlasov.springwebflux.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.javlasov.springwebflux.models.Comment;

public interface CommentRepository extends ReactiveMongoRepository<Comment, Long> {

    Flux<Comment> findCommentsByBookId(String bookId);

    Mono<Void> deleteByBookId(String bookId);

}
