package com.eyo.api_test.student;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table
public class Student {
    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    private Long id;
    @NonNull
    private String name;
    @Transient
    @Getter(AccessLevel.NONE)
    private Integer age;
    @NonNull
    private LocalDate dob;
    @NonNull
    private String email;

    public Integer getAge() {
        return Period.between(dob, LocalDate.now()).getYears();
    }
}
