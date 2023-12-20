package ru.javlasov.secondHomework.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.javlasov.secondHomework.config.FileNameProvider;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@DisplayName("Test dao class")
class CsvQuestionDaoTest {

    private static final String CSV_QUESTIONS_FILE_NAME = "questionsTest.csv";

    private final FileNameProvider mockFileNameProvider = Mockito.mock(FileNameProvider.class);
    private final CsvQuestionDao underTestService = new CsvQuestionDao(mockFileNameProvider);

    @Test
    @DisplayName("Test count questions from csv file")
    void testCountAnswersFromCSV() {
        // given
        var expectedSizeOfAnswers = 2;

        //when
        when(mockFileNameProvider.getCSVFileName()).thenReturn(CSV_QUESTIONS_FILE_NAME);

        //then
        var actualSizeOfAnswers = underTestService.findAll().size();

        assertEquals(expectedSizeOfAnswers, actualSizeOfAnswers);
    }

    @Test
    @DisplayName("Get first question and equal it in expected")
    void testFirstQuestionFromCSVFile() {
        //given
        var expectedFirstQuestion = "What is the number of The Elder Scrolls Skyrim?";

        //when
        when(mockFileNameProvider.getCSVFileName()).thenReturn(CSV_QUESTIONS_FILE_NAME);

        //then
        var actualFirstQuestion = underTestService.findAll().get(0).text();

        assertEquals(expectedFirstQuestion, actualFirstQuestion);
    }

}