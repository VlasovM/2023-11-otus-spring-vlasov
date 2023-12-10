package ru.javlasov.firstHomework.parsingCSV.dao;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import ru.javlasov.firstHomework.parsingCSV.config.FileNameProvider;
import ru.javlasov.firstHomework.parsingCSV.dao.dto.QuestionDto;
import ru.javlasov.firstHomework.parsingCSV.domain.Question;
import ru.javlasov.firstHomework.parsingCSV.exceptions.QuestionReadException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class CsvQuestionDao implements QuestionDao {

    private final FileNameProvider fileNameProvider;

    @Override
    public List<Question> findAll() {
        try (var inputStream = getClass().getClassLoader().getResourceAsStream(fileNameProvider.getCSVFileName())) {
            if (inputStream != null) {
                return processCsvFile(inputStream);
            }
            return new ArrayList<>();
        } catch (QuestionReadException | IOException questionReadException) {
            questionReadException.printStackTrace();
            return new ArrayList<>();
        }
    }

    private List<Question> processCsvFile(InputStream inputStream) {
        var reader = new InputStreamReader(inputStream);
        var csvReader = new CsvToBeanBuilder<QuestionDto>(reader)
                .withType(QuestionDto.class)
                .withSeparator(';')
                .withIgnoreLeadingWhiteSpace(true)
                .withIgnoreEmptyLine(true)
                .withSkipLines(1)
                .build();
        return csvReader.parse().stream()
                .map(QuestionDto::toDomainObject)
                .collect(Collectors.toList());
    }

}
