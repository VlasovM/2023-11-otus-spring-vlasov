package ru.javlasov.firstHomework.service.impl;

import lombok.RequiredArgsConstructor;
import ru.javlasov.firstHomework.dao.QuestionDao;
import ru.javlasov.firstHomework.domain.Answer;
import ru.javlasov.firstHomework.service.CsvFileDisplayService;
import ru.javlasov.firstHomework.service.IOService;

@RequiredArgsConstructor
public class CsvFileDisplayServiceImpl implements CsvFileDisplayService {

    private final IOService ioService;

    private final QuestionDao questionDao;

    @Override
    public void executeTest() {
        var allQuestions = questionDao.findAll();
        allQuestions.forEach(element -> {
            ioService.printLine("\n" + element.text());
            int numberAnswer = 1;
            for (Answer answer : element.answers()) {
                ioService.printFormattedLine("%d, %s - %s", numberAnswer, answer.text(), answer.isCorrect());
                numberAnswer++;
            }
        });
    }

}
