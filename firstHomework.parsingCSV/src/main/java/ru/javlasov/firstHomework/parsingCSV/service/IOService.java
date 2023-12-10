package ru.javlasov.firstHomework.parsingCSV.service;

import ru.javlasov.firstHomework.parsingCSV.domain.Answer;

import java.util.List;

public interface IOService {
    void printLine(String s);

    void printFormattedLine(String s, Object ...args);

    void printAnswers(List<Answer> answerList);

}
