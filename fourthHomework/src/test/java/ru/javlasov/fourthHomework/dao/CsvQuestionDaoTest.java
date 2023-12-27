package ru.javlasov.fourthHomework.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.javlasov.fourthHomework.config.FileNameProvider;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@DisplayName("Test dao class")
class CsvQuestionDaoTest {

    private static final String CSV_QUESTIONS_FILE_NAME_EN = "questionsTestEN.csv";
    private static final String CSV_QUESTIONS_FILE_NAME_RU = "questionsTestRU.csv";

    private final FileNameProvider mockFileNameProvider = Mockito.mock(FileNameProvider.class);
    private final CsvQuestionDao underTestService = new CsvQuestionDao(mockFileNameProvider);

    @Test
    @DisplayName("Test count questions from csv file")
    void testCountAnswersFromCSV() {
        // given
        var expectedSizeOfAnswers = 2;

        //when
        when(mockFileNameProvider.getTestFileName()).thenReturn(CSV_QUESTIONS_FILE_NAME_EN);

        //then
        var actualSizeOfAnswers = underTestService.findAll().size();

        assertEquals(expectedSizeOfAnswers, actualSizeOfAnswers);
    }

    @Test
    @DisplayName("Get first question and equal it in expected (EN)")
    void testFirstQuestionFromCSVFileEN() {
        //given
        var expectedFirstQuestion = "What is the number of The Elder Scrolls Skyrim?";

        //when
        when(mockFileNameProvider.getTestFileName()).thenReturn(CSV_QUESTIONS_FILE_NAME_EN);

        //then
        var actualFirstQuestion = underTestService.findAll().get(0).text();

        assertEquals(expectedFirstQuestion, actualFirstQuestion);
    }

    @Test
    @DisplayName("Get first question and equal it in expected (RU)")
    void testFirstQuestionFromCSVFileRU() {
        //given
        var expectedFirstQuestion = "Какая по счёту часть The elder Scrolls Skyrim?";

        //when
        when(mockFileNameProvider.getTestFileName()).thenReturn(CSV_QUESTIONS_FILE_NAME_RU);

        //then
        var actualFirstQuestion = underTestService.findAll().get(0).text();

        assertEquals(expectedFirstQuestion, actualFirstQuestion);
    }

}