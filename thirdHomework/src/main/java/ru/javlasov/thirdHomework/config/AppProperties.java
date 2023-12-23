package ru.javlasov.thirdHomework.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Setter
@Getter
@Component
@RequiredArgsConstructor
public class AppProperties implements TestConfig, FileNameProvider, LocalConfig {

    @Value("${test.rightAnswersCountToPass}")
    private Integer rightAnswersCountToPass;

    @Value("${test.fileNameRU}")
    private String fileNameRU;

    @Value("${test.fileNameEN}")
    private String fileNameEN;

    @Value("${spring.messages.encoding}")
    private String encoding;

    @Value("${spring.messages.basename}")
    private String baseName;

    @Override
    public int getRightAnswersCountToPass() {
        return rightAnswersCountToPass;
    }

    @Override
    public String getCSVFileName(Locale locale) {
        if (locale.equals(Locale.ENGLISH)) {
            return fileNameEN;
        }
        return fileNameRU;
    }

    @Override
    public String getMessage(String code, Object[] args, Locale locale) {
        ReloadableResourceBundleMessageSource messageSource
                = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename(baseName);
        messageSource.setDefaultEncoding(encoding);
        return messageSource.getMessage(code, args, locale);
    }

    @Override
    public String getMessage(String code, Locale locale) {
        ReloadableResourceBundleMessageSource messageSource
                = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename(baseName);
        messageSource.setDefaultEncoding(encoding);
        return messageSource.getMessage(code, null, locale);
    }

}
