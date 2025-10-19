package calculator;

import camp.nextstep.edu.missionutils.Console;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class Application {
    public static void main(String[] args) {
        // 콘솔 출력 UTF-8 강제 (빌드 스크립트 변경 없이)
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        System.setErr(new PrintStream(System.err, true, StandardCharsets.UTF_8));

        StringCalculator calculator = new StringCalculator();

        System.out.println("덧셈할 문자열을 입력해 주세요.");
        String first = Console.readLine();

        // ✅ 커스텀 구분자 헤더만 있고(//...), 같은 줄에 리터럴 "\n"도 실제 개행도 없을 때만 2번째 줄을 추가로 읽음
        if (first != null
                && first.startsWith("//")
                && !first.contains("\\n")   // 리터럴 \n 인라인 입력이면 두 줄 읽지 않음
                && !first.contains("\n")) { // 실제 개행도 없으면
            String second = Console.readLine();
            if (second != null) {
                first = first + "\n" + second;
            }
        }

        // 예외는 잡지 말고 그대로 던져서 “예외 발생 후 종료” 요건 충족
        int result = calculator.calculate(first);
        System.out.println("결과 : " + result);
    }
}
