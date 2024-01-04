package ru.javlasov.fourthHomework.service;

import ru.javlasov.fourthHomework.domain.Student;
import ru.javlasov.fourthHomework.domain.TestResult;

public interface TestService {

    TestResult executeTestFor(Student student);

}
