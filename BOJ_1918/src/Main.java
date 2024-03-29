import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    static String input;
    static StringBuilder answer = new StringBuilder();
    static Stack<Character> stk = new Stack<>();

    static boolean isNumber(char c) {
        return c >= 'A' && c <= 'Z';
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input = br.readLine();
        int len = input.length();
        for (int i = 0; i < len; i++) {
            char x = input.charAt(i);

            if (isNumber(x)) {
                answer.append(x);
                continue;
            }

            if (x == '(') {
                stk.add(x);
                continue;
            }

            if (x == ')') {
                while (stk.peek() != '(') {
                    answer.append(stk.pop());
                }
                stk.pop();
                continue;
            }

            if (x == '*' || x == '/') {
                while (!stk.isEmpty() && (stk.peek() == '*' || stk.peek() == '/')) {
                    answer.append(stk.pop());
                }
                stk.add(x);
                continue;
            }

            if (x == '+' || x == '-') {
                while (!stk.isEmpty() && (stk.peek() != '(')) {
                    answer.append(stk.pop());
                }
                stk.add(x);
            }
        }

        while (!stk.isEmpty()) {
            answer.append(stk.pop());
        }
        System.out.println(answer);
    }
}
