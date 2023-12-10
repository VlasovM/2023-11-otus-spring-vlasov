package ru.javlasov.firstHomework.parsingCSV.config;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AppProperties implements FileNameProvider {

    private String csvFileName;

    @Override
    public String getCSVFileName() {
        return csvFileName;
    }

}
