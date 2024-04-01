import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] input = new int[N];


        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }

        int[] answer = new int[N];
        Arrays.fill(answer, -1);
        Stack<Integer> stk = new Stack<>();

        stk.add(0);

        for (int i = 1; i < N; i++) {

            while (!stk.isEmpty() && input[stk.peek()] < input[i]) {
                answer[stk.pop()] = input[i];
            }
            stk.add(i);
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            sb.append(answer[i]).append(" ");
        }
        System.out.println(sb);
    }
}
