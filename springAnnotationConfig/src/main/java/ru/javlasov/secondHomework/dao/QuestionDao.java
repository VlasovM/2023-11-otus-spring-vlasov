package ru.javlasov.secondHomework.dao;

import ru.javlasov.secondHomework.domain.Question;

import java.util.List;

public interface QuestionDao {
    List<Question> findAll();
}
