package ru.javlasov.fourthHomework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.javlasov.fourthHomework.domain.Student;
import ru.javlasov.fourthHomework.service.LocalizedIOService;
import ru.javlasov.fourthHomework.service.StudentService;

@RequiredArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {

    private final LocalizedIOService ioService;

    @Override
    public Student determineCurrentStudent() {
        var firstName = ioService.readStringWithPromptLocalized("user.firstName");
        var lastName = ioService.readStringWithPromptLocalized("user.lastName");
        return new Student(firstName, lastName);
    }

}
