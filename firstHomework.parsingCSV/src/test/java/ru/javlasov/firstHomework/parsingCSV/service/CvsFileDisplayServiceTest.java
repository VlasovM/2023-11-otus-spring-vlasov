package ru.javlasov.firstHomework.parsingCSV.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.javlasov.firstHomework.parsingCSV.dao.QuestionDao;
import ru.javlasov.firstHomework.parsingCSV.dao.dto.QuestionDto;
import ru.javlasov.firstHomework.parsingCSV.domain.Answer;
import ru.javlasov.firstHomework.parsingCSV.domain.Question;
import ru.javlasov.firstHomework.parsingCSV.service.impl.CsvFileDisplayServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@DisplayName("Класс CsvFileDisplayService")
public class CvsFileDisplayServiceTest {

    private final IOService mockIoService = Mockito.mock(IOService.class);
    private final QuestionDao mockDao = Mockito.mock(QuestionDao.class);
    private final CsvFileDisplayService underTestService = new CsvFileDisplayServiceImpl(mockIoService, mockDao);

    @Test
    @DisplayName("Попытка написать тест")
    void shouldCorrectPrintInformation() {
        //given

        //then
        when(mockDao.findAll()).thenReturn(getMockListQuestions());
        underTestService.executeTest();

        //when
    }

    private List<Question> getMockListQuestions() {
        var resultList = new ArrayList<Question>();
        resultList.add(getMockQuestion());
        return resultList;
    }

    private Question getMockQuestion() {
        var questionDto = new QuestionDto();
        questionDto.setText("Is test correct?");
        var answerFirst = new Answer("Normally", true);
        var answerSecond = new Answer("No. You need to study the testing material", false);
        var answers = new ArrayList<Answer>();
        answers.add(answerFirst);
        answers.add(answerSecond);
        questionDto.setAnswers(answers);
        return questionDto.toDomainObject();
    }

}
