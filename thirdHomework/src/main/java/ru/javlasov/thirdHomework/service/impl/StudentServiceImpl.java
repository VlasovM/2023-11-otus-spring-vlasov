package ru.javlasov.thirdHomework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.javlasov.thirdHomework.config.LocalConfig;
import ru.javlasov.thirdHomework.domain.Student;
import ru.javlasov.thirdHomework.service.IOService;
import ru.javlasov.thirdHomework.service.StudentService;

import java.util.Locale;

@RequiredArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {

    private final IOService ioService;

    private final LocalConfig localConfig;

    @Override
    public Student determineCurrentStudent(Locale locale) {
        var firstName = ioService.readStringWithPrompt(localConfig.getMessage("user.firstName", locale));
        var lastName = ioService.readStringWithPrompt(localConfig.getMessage("user.lastName", locale));
        return new Student(firstName, lastName);
    }

}
