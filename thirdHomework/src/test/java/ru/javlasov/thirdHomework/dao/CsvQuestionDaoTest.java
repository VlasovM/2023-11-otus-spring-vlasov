package ru.javlasov.thirdHomework.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.context.i18n.LocaleContextHolder;
import ru.javlasov.thirdHomework.config.FileNameProvider;
import ru.javlasov.thirdHomework.config.LocalConfig;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@DisplayName("Test dao class")
class CsvQuestionDaoTest {

    private static final String CSV_QUESTIONS_FILE_NAME_EN = "questionsTestEN.csv";
    private static final String CSV_QUESTIONS_FILE_NAME_RU = "questionsTestRU.csv";

    private final FileNameProvider mockFileNameProvider = Mockito.mock(FileNameProvider.class);
    private final LocalConfig mockLocalConfig = Mockito.mock(LocalConfig.class);
    private final CsvQuestionDao underTestService = new CsvQuestionDao(mockFileNameProvider, mockLocalConfig);

    @Test
    @DisplayName("Test count questions from csv file")
    void testCountAnswersFromCSV() {
        // given
        var expectedSizeOfAnswers = 2;

        //when
        when(mockFileNameProvider.getCSVFileName(Locale.ENGLISH)).thenReturn(CSV_QUESTIONS_FILE_NAME_EN);

        //then
        var actualSizeOfAnswers = underTestService.findAll(Locale.ENGLISH).size();

        assertEquals(expectedSizeOfAnswers, actualSizeOfAnswers);
    }

    @Test
    @DisplayName("Get first question and equal it in expected (EN)")
    void testFirstQuestionFromCSVFileEN() {
        //given
        var expectedFirstQuestion = "What is the number of The Elder Scrolls Skyrim?";

        //when
        when(mockFileNameProvider.getCSVFileName(Locale.ENGLISH)).thenReturn(CSV_QUESTIONS_FILE_NAME_EN);

        //then
        var actualFirstQuestion = underTestService.findAll(Locale.ENGLISH).get(0).text();

        assertEquals(expectedFirstQuestion, actualFirstQuestion);
    }

    @Test
    @DisplayName("Get first question and equal it in expected (RU)")
    void testFirstQuestionFromCSVFileRU() {
        //given
        var expectedFirstQuestion = "Какая по счёту часть The elder Scrolls Skyrim?";

        //when
        when(mockFileNameProvider.getCSVFileName(LocaleContextHolder.getLocale())).thenReturn(CSV_QUESTIONS_FILE_NAME_RU);

        //then
        var actualFirstQuestion = underTestService.findAll(LocaleContextHolder.getLocale()).get(0).text();

        assertEquals(expectedFirstQuestion, actualFirstQuestion);
    }

}