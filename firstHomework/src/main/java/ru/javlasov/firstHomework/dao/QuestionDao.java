package ru.javlasov.firstHomework.dao;

import ru.javlasov.firstHomework.domain.Question;

import java.util.List;

public interface QuestionDao {
    List<Question> findAll();
}
