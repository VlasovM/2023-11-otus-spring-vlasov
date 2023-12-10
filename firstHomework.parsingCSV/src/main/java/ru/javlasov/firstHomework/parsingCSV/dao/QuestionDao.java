package ru.javlasov.firstHomework.parsingCSV.dao;

import ru.javlasov.firstHomework.parsingCSV.domain.Question;

import java.util.List;

public interface QuestionDao {
    List<Question> findAll();
}
