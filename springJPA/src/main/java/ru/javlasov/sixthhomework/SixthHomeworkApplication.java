package ru.javlasov.sixthhomework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.h2.tools.Console;

import java.sql.SQLException;

@SpringBootApplication
public class SixthHomeworkApplication {

    public static void main(String[] args) throws SQLException {
        SpringApplication.run(SixthHomeworkApplication.class, args);
        Console.main(args);
    }

}
