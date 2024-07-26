import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] numbers;
    static int[][] query;

    static int[][] dp;

    static void isPalindrome() {
        for (int i = 1; i <= N; i++) {
            dp[i][i] = 1;
        }

        for (int i = 1; i < N; i++) {
            if (numbers[i] == numbers[i + 1]) {
                dp[i][i + 1] = 1;
            } else {
                dp[i][i + 1] = 0;
            }
        }

        for (int i = 2; i <= N - 1; i++) {
            for (int j = 1; j <= N - i; j++) {
                if (dp[j][j + i] < 0) {
                    if (dp[j + 1][j + i - 1] == 1 && numbers[j] == numbers[j + i]) {
                        dp[j][j + i] = 1;
                    } else {
                        dp[j][j + i] = 0;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        numbers = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        query = new int[M][2];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            query[i][0] = Integer.parseInt(st.nextToken());
            query[i][1] = Integer.parseInt(st.nextToken());
        }
        dp = new int[N + 1][N + 1];
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= N; j++) {
                dp[i][j] = -1;
            }
        }
        isPalindrome();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            sb.append(dp[query[i][0]][query[i][1]]).append("\n");
        }

        System.out.print(sb);
    }
}
