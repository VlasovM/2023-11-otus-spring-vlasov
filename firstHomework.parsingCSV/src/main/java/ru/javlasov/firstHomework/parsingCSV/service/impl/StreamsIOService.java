package ru.javlasov.firstHomework.parsingCSV.service.impl;

import ru.javlasov.firstHomework.parsingCSV.domain.Answer;
import ru.javlasov.firstHomework.parsingCSV.service.IOService;

import java.io.PrintStream;
import java.util.List;

public class StreamsIOService implements IOService {
    private final PrintStream printStream;

    public StreamsIOService(PrintStream printStream) {
        this.printStream = printStream;
    }

    @Override
    public void printLine(String s) {
        printStream.println(s);
    }

    @Override
    public void printFormattedLine(String s, Object... args) {
        printStream.printf(s + "%n", args);
    }

    @Override
    public void printAnswers(List<Answer> answerList) {
        answerList.forEach(answer -> printFormattedLine(answer.text()));
    }

}
