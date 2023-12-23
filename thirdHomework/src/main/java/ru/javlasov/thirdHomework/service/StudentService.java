package ru.javlasov.thirdHomework.service;

import ru.javlasov.thirdHomework.domain.Student;

import java.util.Locale;

public interface StudentService {

    Student determineCurrentStudent(Locale locale);

}
