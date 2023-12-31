package ru.javlasov.thirdHomework.service.impl;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Service;
import ru.javlasov.thirdHomework.config.LocateConfig;
import ru.javlasov.thirdHomework.service.LocalizedMessagesService;

@RequiredArgsConstructor
@Getter
@Setter
@Service
@ConfigurationProperties(prefix = "spring.messages")
public class LocalizedMessagesServiceImpl implements LocalizedMessagesService {

    private String encoding;

    private String baseName;

    private LocateConfig localeConfig;

    @Override
    public String getMessage(String code, Object ...args) {
        ReloadableResourceBundleMessageSource messageSource
                = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename(baseName);
        messageSource.setDefaultEncoding(encoding);
        return messageSource.getMessage(code, args, localeConfig.getLocale());
    }

}
