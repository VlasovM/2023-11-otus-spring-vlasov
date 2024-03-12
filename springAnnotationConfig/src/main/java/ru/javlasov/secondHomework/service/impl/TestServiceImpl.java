package ru.javlasov.secondHomework.service.impl;

import lombok.RequiredArgsConstructor;
import ru.javlasov.secondHomework.dao.QuestionDao;
import ru.javlasov.secondHomework.domain.Answer;
import ru.javlasov.secondHomework.domain.Student;
import ru.javlasov.secondHomework.domain.TestResult;
import ru.javlasov.secondHomework.service.IOService;
import ru.javlasov.secondHomework.service.TestService;

@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final IOService ioService;

    private final QuestionDao questionDao;

    @Override
    public TestResult executeTestFor(Student student) {
        ioService.printLine("");
        ioService.printFormattedLine("Please answer the questions below:");
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
                    "You print incorrect number of answer, try again");
            var isAnswerValid = question.answers().get(studentAnswer - 1).isCorrect();
            testResult.applyAnswer(question, isAnswerValid);
        }
        return testResult;
    }


}
