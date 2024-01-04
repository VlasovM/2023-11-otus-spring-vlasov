package ru.javlasov.fourthHomework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import ru.javlasov.fourthHomework.config.AppProperties;
import ru.javlasov.fourthHomework.service.LocaleService;
import ru.javlasov.fourthHomework.service.LocalizedIOService;

import java.util.HashMap;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class LocaleServiceImpl implements LocaleService {

    private final AppProperties appProperties;

    private final LocalizedIOService ioService;

    @Override
    public void chooseLanguage() {
        var fileNameByLocaleTagMap = new HashMap<String, String>();
        ioService.printFormattedLine("Please choose your language: EN or RU");
        while (true) {
            var language = ioService.readString();
            if (language.equals("EN")) {
                fileNameByLocaleTagMap.put(Locale.ENGLISH.toLanguageTag(), "questionsEN.csv");
                appProperties.setLocale(Locale.ENGLISH);
                appProperties.setFileNameByLocaleTag(fileNameByLocaleTagMap);
                break;
            } else if (language.equals("RU")) {
                fileNameByLocaleTagMap.put(LocaleContextHolder.getLocale().toLanguageTag(), "questionsRU.csv");
                appProperties.setLocale(LocaleContextHolder.getLocale());
                appProperties.setFileNameByLocaleTag(fileNameByLocaleTagMap);
                break;
            } else {
                ioService.printLine("Incorrect language. Please type EN or RU");
            }
        }
    }

}
