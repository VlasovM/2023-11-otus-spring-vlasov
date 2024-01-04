package ru.javlasov.fourthHomework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.javlasov.fourthHomework.dao.QuestionDao;
import ru.javlasov.fourthHomework.domain.Answer;
import ru.javlasov.fourthHomework.domain.Student;
import ru.javlasov.fourthHomework.domain.TestResult;
import ru.javlasov.fourthHomework.service.LocalizedIOService;
import ru.javlasov.fourthHomework.service.TestService;

@RequiredArgsConstructor
@Service
public class TestServiceImpl implements TestService {

    private final LocalizedIOService ioService;

    private final QuestionDao questionDao;

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

}
