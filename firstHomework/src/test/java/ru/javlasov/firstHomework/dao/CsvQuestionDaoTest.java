package ru.javlasov.firstHomework.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.javlasov.firstHomework.config.FileNameProvider;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@DisplayName("TestingDaoClass")
class CsvQuestionDaoTest {

    private static final String CSV_QUESTIONS_FILE_NAME = "questionsTest.csv";

    private final FileNameProvider mockFileNameProvider = Mockito.mock(FileNameProvider.class);
    private final CsvQuestionDao underTestService = new CsvQuestionDao(mockFileNameProvider);

    @Test
    @DisplayName("Test for answers count from CSV file")
    void testCountAnswersFromCSV() {
        // given
        var expectedSizeOfAnswers = 5;

        //when
        when(mockFileNameProvider.getCSVFileName()).thenReturn(CSV_QUESTIONS_FILE_NAME);

        //then
        var actualSizeOfAnswers = underTestService.findAll().size();

        assertEquals(expectedSizeOfAnswers, actualSizeOfAnswers);
    }

    @Test
    @DisplayName("Get first question from csv file and equal it for expected")
    void testFirstQuestionFromCSVFile() {
        //given
        var expectedFirstQuestion = "Who killed Mark?";

        //when
        when(mockFileNameProvider.getCSVFileName()).thenReturn(CSV_QUESTIONS_FILE_NAME);

        //then
        var actualFirstQuestion = underTestService.findAll().get(0).text();

        assertEquals(expectedFirstQuestion, actualFirstQuestion);
    }

}