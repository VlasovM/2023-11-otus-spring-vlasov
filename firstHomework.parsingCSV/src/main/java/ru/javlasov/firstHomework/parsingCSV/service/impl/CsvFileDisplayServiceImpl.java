package ru.javlasov.firstHomework.parsingCSV.service.impl;

import lombok.RequiredArgsConstructor;
import ru.javlasov.firstHomework.parsingCSV.dao.QuestionDao;
import ru.javlasov.firstHomework.parsingCSV.service.CsvFileDisplayService;
import ru.javlasov.firstHomework.parsingCSV.service.IOService;

@RequiredArgsConstructor
public class CsvFileDisplayServiceImpl implements CsvFileDisplayService {

    private final IOService ioService;

    private final QuestionDao questionDao;

    @Override
    public void executeTest() {
        var allQuestions = questionDao.findAll();
        allQuestions.forEach(element -> {
            ioService.printLine("\n" + element.text());
            ioService.printAnswers(element.answers());
        });
    }

}
