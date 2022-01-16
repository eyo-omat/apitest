package com.eyo.api_test.student;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class StudentService {

    public List<Student> getStudents() {
        return List.of(
                new Student(
                        1L,
                        "Kojo",
                        38,
                        LocalDate.of(1999, Month.AUGUST, 7),
                        "kj@mail.com"

                )
        );
    }
}