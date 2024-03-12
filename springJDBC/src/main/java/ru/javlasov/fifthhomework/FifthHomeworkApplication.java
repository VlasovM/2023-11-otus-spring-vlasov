package ru.javlasov.fifthhomework;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class FifthHomeworkApplication {

    public static void main(String[] args) throws SQLException {
        SpringApplication.run(FifthHomeworkApplication.class, args);
        Console.main(args);
    }

}
