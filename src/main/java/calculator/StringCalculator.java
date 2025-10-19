package calculator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {
    public int calculate(String input) {
        if (input == null || input.isEmpty()) {
            return 0;
        }

        Matcher m = Pattern.compile("//(.)\\\\n(.*)").matcher(input);
        if (m.find()) {
            String customDelimiter = m.group(1);
            String[] numbers = m.group(2).split(customDelimiter);
            return sum(numbers);
        }

        String[] numbers = input.split("[,:]");
        return sum(numbers);
    }

    private int sum(String[] numbers) {
        int sum = 0;
        for (String numberStr : numbers) {
            try {
                int number = Integer.parseInt(numberStr.trim());
                if (number < 0) {
                    throw new IllegalArgumentException("음수는 입력할 수 없습니다.");
                }
                sum += number;
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("숫자가 아닌 값이 포함되어 있습니다.");
            }
        }
        return sum;
    }
}
