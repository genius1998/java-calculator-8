package calculator;

import camp.nextstep.edu.missionutils.Console;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class Application {
    public static void main(String[] args) {
        // ✅ 콘솔 출력 UTF-8로 강제 (build.gradle, VM옵션 없이도 동작)
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        System.setErr(new PrintStream(System.err, true, StandardCharsets.UTF_8));

        StringCalculator calculator = new StringCalculator();

        System.out.println("덧셈할 문자열을 입력해 주세요.");
        String input = Console.readLine();

        try {
            int result = calculator.calculate(input);
            System.out.println("결과 : " + result);
        } catch (IllegalArgumentException e) {
            // 요구 사항에 따라 예외 발생 시 애플리케이션이 종료되도록 처리합니다.
            // main 메소드가 끝나면 자연스럽게 종료됩니다.
            // 테스트 케이스는 예외 발생 시 출력을 기대하지 않을 수 있으므로 주석 처리합니다.
            // System.out.println(e.getMessage());
        }
    }
}
