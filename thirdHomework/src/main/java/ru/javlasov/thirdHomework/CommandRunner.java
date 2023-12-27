package ru.javlasov.thirdHomework;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.javlasov.thirdHomework.service.RunnerService;

@RequiredArgsConstructor
@Component
public class CommandRunner implements CommandLineRunner {

    private final RunnerService runnerService;

    @Override
    public void run(String... args) throws Exception {
        runnerService.run();
    }

}
