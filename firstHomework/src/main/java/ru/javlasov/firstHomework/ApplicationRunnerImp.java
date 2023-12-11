package ru.javlasov.firstHomework;

import lombok.RequiredArgsConstructor;
import ru.javlasov.firstHomework.service.CsvFileDisplayService;

@RequiredArgsConstructor
public class ApplicationRunnerImp implements ApplicationRunner {

    private final CsvFileDisplayService csvFileDisplayService;

    @Override
    public void run() {
        csvFileDisplayService.executeTest();
    }

}
