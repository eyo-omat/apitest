package com.eyo.api_test.student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Student {
    private Long id;
    @NonNull
    private String name;
    @NonNull
    private Integer age;
    @NonNull
    private LocalDate dob;
    @NonNull
    private String email;
}
