package ru.javlasov.thirdHomework.service;

import ru.javlasov.thirdHomework.domain.Student;
import ru.javlasov.thirdHomework.domain.TestResult;

import java.util.Locale;

public interface TestService {

    TestResult executeTestFor(Student student, Locale locale);

    Locale chooseLanguage();

}
