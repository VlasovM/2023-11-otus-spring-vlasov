package ru.javlasov.thirdHomework.config;

import java.util.Locale;

public interface LocalConfig {

    String getMessage(String code, Object[] args, Locale locale);

    String getMessage(String code, Locale locale);

}
