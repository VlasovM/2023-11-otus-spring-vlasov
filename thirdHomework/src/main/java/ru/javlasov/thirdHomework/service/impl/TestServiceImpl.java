package ru.javlasov.thirdHomework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import ru.javlasov.thirdHomework.config.AppProperties;
import ru.javlasov.thirdHomework.dao.QuestionDao;
import ru.javlasov.thirdHomework.domain.Answer;
import ru.javlasov.thirdHomework.domain.Student;
import ru.javlasov.thirdHomework.domain.TestResult;
import ru.javlasov.thirdHomework.service.LocalizedIOService;
import ru.javlasov.thirdHomework.service.TestService;

import java.util.HashMap;
import java.util.Locale;

@RequiredArgsConstructor
@Service
public class TestServiceImpl implements TestService {

    private final LocalizedIOService ioService;

    private final QuestionDao questionDao;

    private final AppProperties appProperties;

    @Override
    public TestResult executeTestFor(Student student) {
        ioService.printLine("");
        ioService.printFormattedLineLocalized(("answerQuestion"));
        var questions = questionDao.findAll();
        var testResult = new TestResult(student);
        for (var question : questions) {
            ioService.printFormattedLine("%n %s", question.text());
            int numberAnswer = 1;
            for (Answer answer : question.answers()) {
                ioService.printFormattedLine("%d: %s", numberAnswer, answer.text());
                numberAnswer++;
            }
            var studentAnswer = ioService.readIntForRange(1, question.answers().size(),
                    "incorrectInput");
            var isAnswerValid = question.answers().get(studentAnswer - 1).isCorrect();
            testResult.applyAnswer(question, isAnswerValid);
        }
        return testResult;
    }

    @Override
    public void chooseLanguage() {
        var fileNameByLocaleTagMap = new HashMap<String, String>();
        ioService.printFormattedLine("Please choose your language: EN or RU");
        while (true) {
            var language = ioService.readString();
            if (language.equals("EN")) {
                fileNameByLocaleTagMap.put(Locale.ENGLISH.toLanguageTag(), "questionsEN.csv");
                appProperties.setLocale(Locale.ENGLISH);
                appProperties.setFileNameByLocaleTag(fileNameByLocaleTagMap);
                break;
            } else if (language.equals("RU")) {
                fileNameByLocaleTagMap.put(LocaleContextHolder.getLocale().toLanguageTag(), "questionsRU.csv");
                appProperties.setLocale(LocaleContextHolder.getLocale());
                appProperties.setFileNameByLocaleTag(fileNameByLocaleTagMap);
                break;
            } else {
                ioService.printLine("Incorrect language. Please type EN or RU");
            }
        }
    }

}
