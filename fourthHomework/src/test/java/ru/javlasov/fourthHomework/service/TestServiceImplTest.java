package ru.javlasov.fourthHomework.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.javlasov.fourthHomework.dao.CsvQuestionDao;
import ru.javlasov.fourthHomework.domain.Answer;
import ru.javlasov.fourthHomework.domain.Question;
import ru.javlasov.fourthHomework.domain.Student;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@DisplayName("Testing service class")
@SpringBootTest(properties = "spring.shell.interactive.enabled=false")
@ExtendWith(SpringExtension.class)
class TestServiceImplTest {

    @MockBean
    private LocalizedIOService mockIoService;

    @MockBean
    private CsvQuestionDao mockCsvQuestionDao;

    @Autowired
    private TestService underTestService;

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