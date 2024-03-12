package ru.javlasov.firstHomework.dao;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import ru.javlasov.firstHomework.config.FileNameProvider;
import ru.javlasov.firstHomework.dao.dto.QuestionDto;
import ru.javlasov.firstHomework.domain.Question;
import ru.javlasov.firstHomework.exceptions.QuestionReadException;

import java.io.InputStream;
import java.io.InputStreamReader;
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
            throw new QuestionReadException("Не удалось прочитать CSV файл");
        } catch (Exception exception) {
            throw new QuestionReadException(exception.getMessage(), exception);
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
