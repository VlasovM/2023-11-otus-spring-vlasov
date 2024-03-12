package ru.javlasov.secondHomework.service;

import ru.javlasov.secondHomework.domain.Student;
import ru.javlasov.secondHomework.domain.TestResult;

public interface TestService {

    TestResult executeTestFor(Student student);

}
