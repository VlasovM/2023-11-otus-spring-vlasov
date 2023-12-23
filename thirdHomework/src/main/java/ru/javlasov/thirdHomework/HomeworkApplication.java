package ru.javlasov.thirdHomework;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.javlasov.thirdHomework.service.RunnerService;

@SpringBootApplication
@RequiredArgsConstructor
public class HomeworkApplication {

    public static void main(String[] args) {
        var run = SpringApplication.run(HomeworkApplication.class, args);
        var runnerServiceImp = run.getBean(RunnerService.class);
        runnerServiceImp.run();
    }

}
