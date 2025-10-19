package calculator;

import camp.nextstep.edu.missionutils.Console;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

public class Application {
    public static void main(String[] args) {
        // UTF-8 강제 출력을 위한 PrintWriter 설정
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(System.out, StandardCharsets.UTF_8));
        
        StringCalculator calculator = new StringCalculator();

        writer.println("덧셈할 문자열을 입력해 주세요.");
        writer.flush(); // 버퍼를 비워 메시지를 즉시 출력

        String input = Console.readLine();

        try {
            int result = calculator.calculate(input);
            writer.println("결과 : " + result);
        } catch (IllegalArgumentException e) {
            writer.println(e.getMessage());
        } finally {
            writer.close(); // PrintWriter 닫기
        }
    }
}