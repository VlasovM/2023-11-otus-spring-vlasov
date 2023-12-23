package ru.javlasov.thirdHomework.service;

import ru.javlasov.thirdHomework.domain.TestResult;

import java.util.Locale;

public interface ResultService {

    void showResult(TestResult testResult, Locale locale);

}
