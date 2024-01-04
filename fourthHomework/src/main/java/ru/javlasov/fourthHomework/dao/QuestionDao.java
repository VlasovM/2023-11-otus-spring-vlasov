package ru.javlasov.fourthHomework.dao;

import ru.javlasov.fourthHomework.domain.Question;

import java.util.List;

public interface QuestionDao {
    List<Question> findAll();
}
