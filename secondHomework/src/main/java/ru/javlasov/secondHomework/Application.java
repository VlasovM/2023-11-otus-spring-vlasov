package ru.javlasov.secondHomework;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.javlasov.secondHomework.config.AppConfig;
import ru.javlasov.secondHomework.service.RunnerService;

public class Application {

    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(AppConfig.class);
        var runner = context.getBean(RunnerService.class);
        runner.run();
    }

}
