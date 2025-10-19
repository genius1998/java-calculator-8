package calculator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {
    public int calculate(String input) {
        if (input == null || input.isEmpty()) {
            return 0;
        }

        // 사용자가 "\\n" 리터럴을 넣은 경우도 커버
        input = input.replace("\\n", "\n");

        // ✅ 전체 매칭(앵커) + 그룹 분리
        Matcher m = Pattern.compile("^//(.+)\\n(.*)$").matcher(input);
        if (m.matches()) {
            String delim = m.group(1);
            if (delim.length() != 1) {
                throw new IllegalArgumentException("커스텀 구분자는 한 글자여야 합니다.");
            }
            String[] numbers = m.group(2).split(Pattern.quote(delim));
            return sum(numbers);
        }

        // 기본 구분자 , 또는 :
        String[] numbers = input.split("[,:]");
        return sum(numbers);
    }

    private int sum(String[] numbers) {
        int sum = 0;
        for (String s : numbers) {
            s = s.trim();
            if (s.isEmpty()) continue; // "1,,2" 같은 엣지 방어
            int n;
            try {
                n = Integer.parseInt(s);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("숫자가 아닌 값이 포함되어 있습니다.");
            }
            if (n < 0) throw new IllegalArgumentException("음수는 입력할 수 없습니다.");
            sum += n;
        }
        return sum;
    }
}
