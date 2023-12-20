package ru.javlasov.secondHomework.service.impl;

import lombok.RequiredArgsConstructor;
import ru.javlasov.secondHomework.domain.Student;
import ru.javlasov.secondHomework.service.IOService;
import ru.javlasov.secondHomework.service.StudentService;

@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final IOService ioService;

    @Override
    public Student determineCurrentStudent() {
        var firstName = ioService.readStringWithPrompt("Please input your first name");
        var lastName = ioService.readStringWithPrompt("Please input your last name");
        return new Student(firstName, lastName);
    }

}
