package com.eyo.api_test.student;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void itShouldCheckIfStudentEmailExists() {

        // given
        String email = "wayne@mail.com";
        Student student = new Student(
                "Bruce", LocalDate.parse("1976-09-18"), email
        );
        underTest.save(student);

        // when
        boolean exists = underTest.selectExistsEmail(email);

        // then
        assertThat(exists).isTrue();
    }

    @Test
    void itShouldCheckIfStudentEmailDoesNotExist() {

        // given
        String email = "wayne@mail.com";

        // when
        boolean exists = underTest.selectExistsEmail(email);

        // then
        assertThat(exists).isFalse();
    }
}