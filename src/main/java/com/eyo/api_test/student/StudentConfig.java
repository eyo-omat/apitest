package com.eyo.api_test.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            Student kojo = new Student(
                    "Kojo",
                    LocalDate.of(1999, Month.AUGUST, 7),
                    "kj@mail.com"

            );
            Student eyo = new Student(
                    "Eyo",
                    LocalDate.of(1979, Month.AUGUST, 7),
                    "eeyo@mail.com"

            );
            repository.saveAll(
                    List.of(kojo, eyo)
            );

        };
    }
}
