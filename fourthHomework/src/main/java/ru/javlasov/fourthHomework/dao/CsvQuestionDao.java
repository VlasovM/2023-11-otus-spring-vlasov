package ru.javlasov.fourthHomework.dao;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.javlasov.fourthHomework.config.FileNameProvider;
import ru.javlasov.fourthHomework.dao.dto.QuestionDto;
import ru.javlasov.fourthHomework.domain.Question;
import ru.javlasov.fourthHomework.exceptions.QuestionReadException;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class CsvQuestionDao implements QuestionDao {

    private final FileNameProvider applicationProperties;

    @Override
    public List<Question> findAll() {
        try (var inputStream = getClass().getClassLoader()
                .getResourceAsStream(applicationProperties.getTestFileName())) {
            if (inputStream != null) {
                return processCsvFile(inputStream);
            }
            throw new QuestionReadException("error.read.file");
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
