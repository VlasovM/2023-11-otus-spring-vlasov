package ru.javlasov.fourthHomework.domain;

import java.util.List;

public record Question(String text, List<Answer> answers) {
}
