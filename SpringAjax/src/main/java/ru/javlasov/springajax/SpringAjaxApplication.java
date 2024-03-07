package ru.javlasov.springajax;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringAjaxApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringAjaxApplication.class, args);
        System.out.printf("Чтобы перейти на страницу сайта открывай: %n%s%n",
                "http://localhost:8080");
    }

}
