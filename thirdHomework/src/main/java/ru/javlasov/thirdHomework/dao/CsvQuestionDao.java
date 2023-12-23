package ru.javlasov.thirdHomework.dao;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.javlasov.thirdHomework.config.FileNameProvider;
import ru.javlasov.thirdHomework.config.LocalConfig;
import ru.javlasov.thirdHomework.dao.dto.QuestionDto;
import ru.javlasov.thirdHomework.domain.Question;
import ru.javlasov.thirdHomework.exceptions.QuestionReadException;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class CsvQuestionDao implements QuestionDao {

    private final FileNameProvider applicationProperties;

    private final LocalConfig localConfig;

    @Override
    public List<Question> findAll(Locale locale) {
        try (var inputStream = getClass().getClassLoader()
                .getResourceAsStream(applicationProperties.getCSVFileName(locale))) {
            if (inputStream != null) {
                return processCsvFile(inputStream);
            }
            throw new QuestionReadException(localConfig.getMessage("error.read.file", locale));
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
