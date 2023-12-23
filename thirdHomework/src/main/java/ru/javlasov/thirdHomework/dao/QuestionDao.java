package ru.javlasov.thirdHomework.dao;

import ru.javlasov.thirdHomework.domain.Question;

import java.util.List;
import java.util.Locale;

public interface QuestionDao {
    List<Question> findAll(Locale locale);
}
