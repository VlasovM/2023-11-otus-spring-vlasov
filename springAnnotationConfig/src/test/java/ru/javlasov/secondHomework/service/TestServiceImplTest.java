package ru.javlasov.secondHomework.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.javlasov.secondHomework.dao.CsvQuestionDao;
import ru.javlasov.secondHomework.domain.Answer;
import ru.javlasov.secondHomework.domain.Question;
import ru.javlasov.secondHomework.domain.Student;
import ru.javlasov.secondHomework.service.impl.TestServiceImpl;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@DisplayName("Testing service class")
class TestServiceImplTest {

    IOService mockIoService = Mockito.mock(IOService.class);
    CsvQuestionDao mockCsvQuestionDao = Mockito.mock(CsvQuestionDao.class);
    TestServiceImpl underTestService = new TestServiceImpl(mockIoService, mockCsvQuestionDao);

    @Test
    @DisplayName("All correct answers")
    void testAllCorrectAnswers() {
        //give
        var expectedCorrectAnswers = 2;

        //when
        when(mockCsvQuestionDao.findAll()).thenReturn(getMockQuestions());
        when(mockIoService.readIntForRange(anyInt(), anyInt(), anyString())).thenReturn(1);

        //then
        var testResult = underTestService.executeTestFor(getMockStudent());
        var actualCorrectAnswers = testResult.getRightAnswersCount();

        assertEquals(expectedCorrectAnswers, actualCorrectAnswers);
    }

    private Student getMockStudent() {
        return new Student("Tod", "Govard");
    }

    private List<Question> getMockQuestions() {
        var questionFirst = new Question("What is the number of The Elder Scrolls Skyrim?",
                Arrays.asList(new Answer("V", true), new Answer("IV", false)));
        var questionSecond = new Question("Between whom and whom is there a civil war in Skyrim?",
                Arrays.asList(new Answer("Empire vs Stormcloak", true),
                        new Answer("Dovakin vs Empire", false)));
        return Arrays.asList(questionFirst, questionSecond);
    }

}