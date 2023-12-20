package ru.javlasov.secondHomework.config;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Setter
@Component
public class AppProperties implements TestConfig, FileNameProvider {

    @Value("${test.rightAnswersCountToPass}")
    private Integer rightAnswersCountToPass;

    @Value("${test.fileName}")
    private String testFileName;

    @Override
    public int getRightAnswersCountToPass() {
        return rightAnswersCountToPass;
    }

    @Override
    public String getCSVFileName() {
        return testFileName;
    }

}
