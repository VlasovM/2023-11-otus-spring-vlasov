package ru.javlasov.thirdHomework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.javlasov.thirdHomework.config.AppProperties;
import ru.javlasov.thirdHomework.domain.TestResult;
import ru.javlasov.thirdHomework.service.IOService;
import ru.javlasov.thirdHomework.service.ResultService;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class ResultServiceImpl implements ResultService {

    private final AppProperties appProperties;

    private final IOService ioService;

    @Override
    public void showResult(TestResult testResult, Locale locale) {
        ioService.printLine("");
        ioService.printLine(appProperties.getMessage("showResult.testResult", locale));
        ioService.printFormattedLine(appProperties.getMessage("showResult.student", locale),
                testResult.getStudent().getFullName());
        ioService.printFormattedLine(appProperties.getMessage("showResult.answered.questions", locale),
                testResult.getAnsweredQuestions().size());
        ioService.printFormattedLine(appProperties.getMessage("showResult.right.answers.count", locale),
                testResult.getRightAnswersCount());
        if (testResult.getRightAnswersCount() >= appProperties.getRightAnswersCountToPass()) {
            ioService.printLine(appProperties.getMessage("showResult.succeed", locale));
            return;
        }
        ioService.printLine(appProperties.getMessage("showResult.fail", locale));
    }

}
