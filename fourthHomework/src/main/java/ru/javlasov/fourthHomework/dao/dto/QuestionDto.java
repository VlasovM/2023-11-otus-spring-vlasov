package ru.javlasov.fourthHomework.dao.dto;

import com.opencsv.bean.CsvBindAndSplitByPosition;
import com.opencsv.bean.CsvBindByPosition;
import lombok.Data;
import org.springframework.stereotype.Component;
import ru.javlasov.fourthHomework.domain.Answer;
import ru.javlasov.fourthHomework.domain.Question;

import java.util.ArrayList;
import java.util.List;

@Data
@Component
public class QuestionDto {

    @CsvBindByPosition(position = 0)
    private String text;

    @CsvBindAndSplitByPosition(position = 1, collectionType = ArrayList.class, elementType = Answer.class,
            converter = AnswerCsvConverter.class, splitOn = "\\|")
    private List<Answer> answers;

    public Question toDomainObject() {
        return new Question(text, answers);
    }

}
