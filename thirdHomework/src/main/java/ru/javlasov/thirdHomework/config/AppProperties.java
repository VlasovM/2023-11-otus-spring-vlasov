package ru.javlasov.thirdHomework.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Map;

@Setter
@Getter
@Component
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "test")
public class AppProperties implements TestConfig, FileNameProvider, LocateConfig {

    private Integer rightAnswersCountToPass;

    private Map<String, String> fileNameByLocaleTag;

    private Locale locale;

    @Override
    public int getRightAnswersCountToPass() {
        return rightAnswersCountToPass;
    }

    @Override
    public String getTestFileName() {
        return fileNameByLocaleTag.get(locale.toLanguageTag());
    }


    @Override
    public Locale getLocale() {
        return locale;
    }

}
