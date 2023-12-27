package ru.javlasov.thirdHomework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.javlasov.thirdHomework.config.AppProperties;
import ru.javlasov.thirdHomework.domain.TestResult;
import ru.javlasov.thirdHomework.service.LocalizedIOService;
import ru.javlasov.thirdHomework.service.ResultService;

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
        ioService.printLine("showResult.fail");
    }

}
