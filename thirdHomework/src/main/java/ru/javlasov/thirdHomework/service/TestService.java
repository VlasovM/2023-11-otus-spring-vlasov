package ru.javlasov.thirdHomework.service;

import ru.javlasov.thirdHomework.domain.Student;
import ru.javlasov.thirdHomework.domain.TestResult;

public interface TestService {

    TestResult executeTestFor(Student student);

    void chooseLanguage();

}
