package calculator;

import camp.nextstep.edu.missionutils.Console;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;

public class Application {
    public static void main(String[] args) {
        // 콘솔 출력 UTF-8 고정 (빌드 수정 없음)
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        System.setErr(new PrintStream(System.err, true, StandardCharsets.UTF_8));

        StringCalculator calculator = new StringCalculator();

        System.out.println("덧셈할 문자열을 입력해 주세요.");
        String first = Console.readLine();

        // ✅ 커스텀 구분자 헤더만 입력됐으면 두 번째 줄을 추가로 읽어서 합쳐준다.
        //   ex) first = "//;"  (사용자가 다음 줄에 "1;2;3" 입력)
        if (first != null && first.startsWith("//") && !first.contains("\\n") && !first.contains("\n")) {
            // 줄바꿈 없이 헤더만 들어온 경우로 판단
            String second = Console.readLine();             // "1;2;3"
            if (second != null) {
                first = first + "\n" + second;              // "//;\n1;2;3" 형태로 만들어 calculate에 전달
            }
        }

        try {
            int result = calculator.calculate(first);
            System.out.println("결과 : " + result);
        } catch (IllegalArgumentException e) {
            // 요구사항: 예외 발생 시 애플리케이션 종료 (메시지 출력 요구 X)
            // 출력하지 않고 종료되도록 둔다.
        }
    }
}
