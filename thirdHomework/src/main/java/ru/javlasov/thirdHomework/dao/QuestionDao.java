package ru.javlasov.thirdHomework.dao;

import ru.javlasov.thirdHomework.domain.Question;

import java.util.List;

public interface QuestionDao {
    List<Question> findAll();
}
