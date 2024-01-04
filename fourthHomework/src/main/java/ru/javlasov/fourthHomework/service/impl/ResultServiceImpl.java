package ru.javlasov.fourthHomework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.javlasov.fourthHomework.config.AppProperties;
import ru.javlasov.fourthHomework.domain.TestResult;
import ru.javlasov.fourthHomework.service.LocalizedIOService;
import ru.javlasov.fourthHomework.service.ResultService;

@Service
@RequiredArgsConstructor
public class ResultServiceImpl implements ResultService {

    private final AppProperties appProperties;

    private final LocalizedIOService ioService;

    @Override
    public void showResult(TestResult testResult) {
        ioService.printLine("");
        ioService.printFormattedLineLocalized("showResult.testResult");
        ioService.printFormattedLineLocalized("showResult.student",
                testResult.getStudent().getFullName());
        ioService.printFormattedLineLocalized("showResult.answered.questions",
                testResult.getAnsweredQuestions().size());
        ioService.printFormattedLineLocalized("showResult.right.answers.count",
                testResult.getRightAnswersCount());
        if (testResult.getRightAnswersCount() >= appProperties.getRightAnswersCountToPass()) {
            ioService.printFormattedLineLocalized("showResult.succeed");
            return;
        }
        ioService.printFormattedLineLocalized("showResult.fail");
    }

}
