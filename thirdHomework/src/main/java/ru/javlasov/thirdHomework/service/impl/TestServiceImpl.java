package ru.javlasov.thirdHomework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import ru.javlasov.thirdHomework.config.LocalConfig;
import ru.javlasov.thirdHomework.dao.QuestionDao;
import ru.javlasov.thirdHomework.domain.Answer;
import ru.javlasov.thirdHomework.domain.Student;
import ru.javlasov.thirdHomework.domain.TestResult;
import ru.javlasov.thirdHomework.service.IOService;
import ru.javlasov.thirdHomework.service.TestService;

import java.util.Locale;

@RequiredArgsConstructor
@Service
public class TestServiceImpl implements TestService {

    private final IOService ioService;

    private final QuestionDao questionDao;

    private final LocalConfig localConfig;

    @Override
    public TestResult executeTestFor(Student student, Locale locale) {
        ioService.printLine("");
        ioService.printFormattedLine(localConfig.getMessage("answerQuestion", locale));
        var questions = questionDao.findAll(locale);
        var testResult = new TestResult(student);
        for (var question : questions) {
            ioService.printFormattedLine("%n %s", question.text());
            int numberAnswer = 1;
            for (Answer answer : question.answers()) {
                ioService.printFormattedLine("%d: %s", numberAnswer, answer.text());
                numberAnswer++;
            }
            var studentAnswer = ioService.readIntForRange(1, question.answers().size(),
                    localConfig.getMessage("incorrectInput", locale));
            var isAnswerValid = question.answers().get(studentAnswer - 1).isCorrect();
            testResult.applyAnswer(question, isAnswerValid);
        }
        return testResult;
    }

    @Override
    public Locale chooseLanguage() {
        ioService.printFormattedLine("Please choose your language: EN or RU");
        Locale locale;
        while (true) {
            var language = ioService.readString();
            if (language.equals("EN")) {
                locale = Locale.ENGLISH;
                break;
            } else if (language.equals("RU")) {
                locale = LocaleContextHolder.getLocale();
                break;
            } else {
                ioService.printLine("Incorrect language. Please type EN or RU");
            }
        }
        return locale;
    }

}
