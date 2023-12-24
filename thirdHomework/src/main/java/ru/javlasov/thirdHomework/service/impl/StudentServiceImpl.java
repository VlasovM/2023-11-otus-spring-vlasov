package ru.javlasov.thirdHomework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.javlasov.thirdHomework.domain.Student;
import ru.javlasov.thirdHomework.service.LocalizedIOService;
import ru.javlasov.thirdHomework.service.StudentService;

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
