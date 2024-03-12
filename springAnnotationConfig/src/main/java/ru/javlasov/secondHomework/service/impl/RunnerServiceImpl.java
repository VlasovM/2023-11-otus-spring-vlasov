package ru.javlasov.secondHomework.service.impl;

import lombok.RequiredArgsConstructor;
import ru.javlasov.secondHomework.service.ResultService;
import ru.javlasov.secondHomework.service.RunnerService;
import ru.javlasov.secondHomework.service.StudentService;
import ru.javlasov.secondHomework.service.TestService;

@RequiredArgsConstructor
public class RunnerServiceImpl implements RunnerService {

    private final TestService testService;

    private final StudentService studentService;

    private final ResultService resultService;

    @Override
    public void run() {
        var student = studentService.determineCurrentStudent();
        var testResult = testService.executeTestFor(student);
        resultService.showResult(testResult);
    }

}
