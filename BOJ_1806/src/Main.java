import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        int sum = 0;
        int start = 0;
        int end = 0;
        int min = N + 10;
        int[] input = new int[N+1];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }

        while (end <= N) {
            if (sum >= S) {
                if (min > end - start) {
                    min = end - start;
                }
                sum -= input[start++];
            } else {
                sum += input[end++];
            }
        }
        if (min == N + 10) {
            System.out.println(0);
            return;
        }

        System.out.println(min);
    }
}
