import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static Stack<Character> leftStk = new Stack<>();
    static Stack<Character> rightStk = new Stack<>();
    static int N;

    static void L() {
        if (!leftStk.isEmpty()) {
            Character c = leftStk.pop();
            rightStk.push(c);
        }
    }

    static void D() {
        if (!rightStk.isEmpty()) {
            Character c = rightStk.pop();
            leftStk.push(c);
        }
    }

    static void B() {
        if (!leftStk.isEmpty()) {
            leftStk.pop();
        }
    }

    static void P(Character add) {
        leftStk.push(add);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        N = input.length();

        for (int i = 0; i < N; i++) {
            leftStk.push(input.charAt(i));
        }

        int M = Integer.parseInt(br.readLine());

        StringTokenizer st;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();

            if (command.equals("L")) {
                L();
                continue;
            }

            if (command.equals("D")) {
                D();
                continue;
            }

            if (command.equals("B")) {
                B();
                continue;
            }

            if (command.equals("P")) {
                String add = st.nextToken();
                P(add.charAt(0));
            }
        }

        StringBuilder answer = new StringBuilder();
        while (!leftStk.isEmpty()) {
            answer.append(leftStk.pop());
        }
        answer.reverse();

        while (!rightStk.isEmpty()) {
            answer.append(rightStk.pop());
        }

        System.out.println(answer);
    }
}
