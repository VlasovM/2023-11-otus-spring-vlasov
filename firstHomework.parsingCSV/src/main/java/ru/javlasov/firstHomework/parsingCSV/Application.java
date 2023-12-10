package ru.javlasov.firstHomework.parsingCSV;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {

    private static final String SPRING_CONTEXT_FILE_NAME = "spring-context.xml";

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(SPRING_CONTEXT_FILE_NAME);
        var applicationRunner = context.getBean(ApplicationRunner.class);
        applicationRunner.run();
    }

}
