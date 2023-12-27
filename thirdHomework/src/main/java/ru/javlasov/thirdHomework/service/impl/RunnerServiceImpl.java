package ru.javlasov.thirdHomework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.javlasov.thirdHomework.service.ResultService;
import ru.javlasov.thirdHomework.service.RunnerService;
import ru.javlasov.thirdHomework.service.StudentService;
import ru.javlasov.thirdHomework.service.TestService;

@Service
@RequiredArgsConstructor
public class RunnerServiceImpl implements RunnerService {

    private final TestService testService;

    private final StudentService studentService;

    private final ResultService resultService;

    @Override
    public void run() {
        testService.chooseLanguage();
        var student = studentService.determineCurrentStudent();
        var testResult = testService.executeTestFor(student);
        resultService.showResult(testResult);
    }

}
