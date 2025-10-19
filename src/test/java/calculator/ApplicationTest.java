package calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StringCalculatorTest {

    private StringCalculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new StringCalculator();
    }

    @Test
    @DisplayName("빈 문자열 또는 null을 입력하면 0을 반환한다.")
    void emptyOrNull() {
        assertThat(calculator.calculate(null)).isZero();
        assertThat(calculator.calculate("")).isZero();
    }

    @ParameterizedTest
    @ValueSource(strings = {"1,2", "1,2,3"})
    @DisplayName("쉼표 구분자로 분리된 숫자의 합을 반환한다.")
    void splitByComma(String input) {
        int expected = 0;
        for (String s : input.split(",")) {
            expected += Integer.parseInt(s);
        }
        assertThat(calculator.calculate(input)).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"'1,2:3', 6", "'1:2:3', 6"})
    @DisplayName("쉼표 또는 콜론 구분자로 분리된 숫자의 합을 반환한다.")
    void splitByCommaAndColon(String input, String expected) {
        assertThat(calculator.calculate(input)).isEqualTo(Integer.parseInt(expected));
    }

    @Test
    @DisplayName("커스텀 구분자로 분리된 숫자의 합을 반환한다.")
    void customDelimiter() {
        assertThat(calculator.calculate("//;\n1;2;3")).isEqualTo(6);
    }

    @Test
    @DisplayName("음수를 입력하면 예외가 발생한다.")
    void negativeNumber() {
        assertThatThrownBy(() -> calculator.calculate("-1,2,3"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("숫자가 아닌 문자를 입력하면 예외가 발생한다.")
    void notNumber() {
        assertThatThrownBy(() -> calculator.calculate("a,2,3"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}