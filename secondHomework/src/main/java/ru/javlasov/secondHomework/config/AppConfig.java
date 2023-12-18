package ru.javlasov.secondHomework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.javlasov.secondHomework.dao.CsvQuestionDao;
import ru.javlasov.secondHomework.dao.QuestionDao;
import ru.javlasov.secondHomework.service.IOService;
import ru.javlasov.secondHomework.service.ResultService;
import ru.javlasov.secondHomework.service.RunnerService;
import ru.javlasov.secondHomework.service.StudentService;
import ru.javlasov.secondHomework.service.TestService;
import ru.javlasov.secondHomework.service.impl.ResultServiceImpl;
import ru.javlasov.secondHomework.service.impl.RunnerServiceImpl;
import ru.javlasov.secondHomework.service.impl.StreamsIOService;
import ru.javlasov.secondHomework.service.impl.StudentServiceImpl;
import ru.javlasov.secondHomework.service.impl.TestServiceImpl;

@Configuration
@PropertySource("classpath:application.properties")
public class AppConfig {

    @Bean
    public RunnerService runnerService(TestService testService, StudentService studentService,
                                       ResultService resultService) {
        return new RunnerServiceImpl(testService, studentService, resultService);
    }

    @Bean
    public AppProperties appProperties() {
        return new AppProperties();
    }

    @Bean
    public ResultService resultService(TestConfig testConfig, IOService ioService) {
        return new ResultServiceImpl(testConfig, ioService);
    }

    @Bean
    public TestConfig testConfig() {
        return new AppProperties();
    }

    @Bean
    public IOService ioService() {
        return new StreamsIOService(java.lang.System.out, java.lang.System.in);
    }

    @Bean
    public StudentService studentService(IOService ioService) {
        return new StudentServiceImpl(ioService);
    }

    @Bean
    public TestService testService(IOService ioService, QuestionDao questionDao) {
        return new TestServiceImpl(ioService, questionDao);
    }

    @Bean
    public QuestionDao questionDao(FileNameProvider fileNameProvider) {
        return new CsvQuestionDao(fileNameProvider);
    }

}
