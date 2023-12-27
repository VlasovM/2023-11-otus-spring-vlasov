package ru.javlasov.fourthHomework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import ru.javlasov.fourthHomework.config.AppProperties;
import ru.javlasov.fourthHomework.domain.TestResult;
import ru.javlasov.fourthHomework.service.ResultService;
import ru.javlasov.fourthHomework.service.RunnerService;
import ru.javlasov.fourthHomework.service.StudentService;
import ru.javlasov.fourthHomework.service.TestService;

@RequiredArgsConstructor
@ShellComponent
public class RunnerServiceImpl implements RunnerService {

    private final TestService testService;

    private final StudentService studentService;

    private final ResultService resultService;

    private final AppProperties appProperties;

    private TestResult testResult;

    @ShellMethod(value = "Choose language", key = {"lan", "language"})
    public void chooseLanguage() {
        testService.chooseLanguage();
    }

    @ShellMethod(value = "run testing", key = {"run", "test"})
    @ShellMethodAvailability(value = "isChooseLanguage")
    public void determineStudent() {
        var student = studentService.determineCurrentStudent();
        testResult = testService.executeTestFor(student);
    }

    @ShellMethod(value = "show result", key = {"show", "results"})
    @ShellMethodAvailability(value = "isCompletedTest")
    public void showResult() {
        resultService.showResult(testResult);
    }

    private Availability isCompletedTest() {
        return testResult == null ? Availability.unavailable("At second complete the test") : Availability.available();
    }

    private Availability isChooseLanguage() {
        return appProperties.getLocale() == null ? Availability.unavailable("At first choose the language") : Availability.available();
    }

}
