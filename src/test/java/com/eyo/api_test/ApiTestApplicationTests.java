package com.eyo.api_test;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class ApiTestApplicationTests {

    Calculator underTest = new Calculator();

    @Test
    void itShouldAddTwoNumbers() {
        // given
        int num1 = 10;
        int num2 = 50;

        //when
        int result = underTest.add(num1, num2);

        //then
        int expected = 60;
        assertThat(result).isEqualTo(expected);

    }

    class Calculator {
        int add(int a, int b) {
            return a+b;
        }
    }
}
