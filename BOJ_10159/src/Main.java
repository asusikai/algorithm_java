import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        int[][] matrix = new int[N + 1][N + 1];

        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= N; j++) {
                matrix[i][j] = 101;
            }
        }

        for (int i = 1; i <= N; i++) {
            matrix[i][i] = 0;
        }

        StringTokenizer st;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            matrix[a][b] = 1;
        }

        for (int mid = 1; mid <= N; mid++) {
            for (int start = 1; start <= N; start++) {
                for (int end = 1; end <= N; end++) {
                    if (matrix[start][end] > matrix[start][mid] + matrix[mid][end]) {
                        matrix[start][end] = matrix[start][mid] + matrix[mid][end];
                    }
                }
            }
        }
        for (int i = 1; i <= N; i++) {
            int answer = 0;
            for (int j = 1; j <= N; j++) {
                if (matrix[i][j] < 101) answer++;
                if (matrix[j][i] < 101) answer++;
            }
            System.out.println(N - answer + 1);
        }
    }
}
