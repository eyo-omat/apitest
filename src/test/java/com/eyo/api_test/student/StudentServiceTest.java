package com.eyo.api_test.student;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    private StudentService underTest;

    @BeforeEach
    void setUp() {
        underTest = new StudentService(studentRepository);
    }

    @Test
    void canGetAllStudents() {
        // when
        underTest.getStudents();

        //then
        verify(studentRepository).findAll();
    }

    @Test
    void canAddNewStudent() {
        //given
        String email = "wayne@mail.com";
        Student student = new Student(
                "Bruce", LocalDate.parse("1976-09-18"), email
        );

        //when
        underTest.addNewStudent(student);

        //then
        ArgumentCaptor<Student> studentArgumentCaptor = ArgumentCaptor.forClass(Student.class);
        verify(studentRepository).save(studentArgumentCaptor.capture());
        Student capturedStudent = studentArgumentCaptor.getValue();

        assertThat(capturedStudent).isEqualTo(student);
    }

    @Test
    void willThrowWhenEmailIsTaken() {
        //given
        String email = "wayne@mail.com";
        Student student = new Student(
                "Bruce", LocalDate.parse("1976-09-18"), email
        );

        given(studentRepository.findStudentByEmail(student.getEmail())).willReturn(Optional.of(student));
        //when


        //then
        assertThatThrownBy(() -> underTest.addNewStudent(student))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("email taken");

        verify(studentRepository, never()).save(any());
    }

    @Test
    void canDeleteAStudent() {
        // given
        Long studentId = 3L;
        given(studentRepository.existsById(studentId)).willReturn(true);
        // when
        underTest.deleteStudent(studentId);

        // then
        verify(studentRepository).deleteById(studentId);
    }

    @Test
    void willThrowWhenIdDoesNotExist() {
        //given
        Long studentId = 3L;
        //when
        //then
        assertThatThrownBy(() -> underTest.deleteStudent(studentId))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("student with id " + studentId + " does not exist");

        verify(studentRepository, never()).deleteById(any());
    }

    @Test
    void canUpdateStudentDetails() {
        //given
        String email = "wayne@mail.com";
        Student student = new Student(
                "Bruce", LocalDate.parse("1976-09-18"), email
        );
        Long studentId = 3L;
        given(studentRepository.findById(studentId)).willReturn(Optional.of(student));

        String name = "Joker";

        // when
        underTest.updateStudent(studentId, name, "email");

        // then

    }

    @Test
    void willThrowWhenStudentDoesNotExist() {
        //given
        String email = "wayne@mail.com";
        Long studentId = 3L;
        String name = "Joker";

        // when
        // then
        assertThatThrownBy(() -> underTest.updateStudent(studentId, name, email))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("student with id " + studentId + " does not exist");
    }

    @Test
    void willThrowWhenEmailExists() {
        //given
        String email = "wayne@mail.com";
        Student student = new Student(
                "Bruce", LocalDate.parse("1976-09-18"), email
        );
        Long studentId = 3L;
        given(studentRepository.findById(studentId)).willReturn(Optional.of(student));

        String name = "Joker";
        given(studentRepository.findStudentByEmail(any())).willReturn(Optional.of(student));

        // when
        // then
        assertThatThrownBy(() -> underTest.updateStudent(studentId, name, "email"))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("email taken");
    }
}