package ru.javlasov.secondHomework.dao.dto;

import com.opencsv.bean.AbstractCsvConverter;
import ru.javlasov.secondHomework.domain.Answer;

public class AnswerCsvConverter extends AbstractCsvConverter {

    @Override
    public Object convertToRead(String value) {
        var valueArr = value.split("%");
        return new Answer(valueArr[0], Boolean.parseBoolean(valueArr[1]));
    }

}
