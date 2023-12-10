package ru.javlasov.firstHomework.parsingCSV;

import lombok.RequiredArgsConstructor;
import ru.javlasov.firstHomework.parsingCSV.service.CsvFileDisplayService;

@RequiredArgsConstructor
public class ApplicationRunnerImp implements ApplicationRunner {

    private final CsvFileDisplayService csvFileDisplayService;

    @Override
    public void run() {
        csvFileDisplayService.executeTest();
    }

}
