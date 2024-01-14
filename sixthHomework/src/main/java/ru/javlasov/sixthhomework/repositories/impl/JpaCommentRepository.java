package ru.javlasov.sixthhomework.repositories.impl;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.javlasov.sixthhomework.models.Comment;
import ru.javlasov.sixthhomework.repositories.CommentRepository;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.repository.EntityGraph.EntityGraphType.FETCH;

@Repository
@RequiredArgsConstructor
public class JpaCommentRepository implements CommentRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public Optional<Comment> findById(long id) {
        return Optional.ofNullable(entityManager.find(Comment.class, id));
    }

    @Override
    public List<Comment> findAllCommentByBookId(long bookId) {
        EntityGraph<?> entityGraph = entityManager.getEntityGraph("book-entity-graph");
        var query = entityManager.createQuery("SELECT c FROM Comment c WHERE c.book.id = :id"
                , Comment.class).setParameter("id", bookId);
        query.setHint(FETCH.getKey(), entityGraph);
        return query.getResultList();
    }

    @Override
    public void delete(long id) {
        var comment = findById(id);
        comment.ifPresent(entityManager::remove);
    }

    @Override
    public Comment saveOrUpdate(Comment comment) {
        if (comment.getId() == 0) {
            entityManager.persist(comment);
            return comment;
        }
        return update(comment);
    }

    private Comment update(Comment comment) {
        return entityManager.merge(comment);
    }
}
