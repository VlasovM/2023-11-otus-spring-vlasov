package ru.javlasov.firstHomework.dao.dto;

import com.opencsv.bean.CsvBindAndSplitByPosition;
import com.opencsv.bean.CsvBindByPosition;
import lombok.Getter;
import lombok.Setter;
import ru.javlasov.firstHomework.domain.Answer;
import ru.javlasov.firstHomework.domain.Question;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
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
